package dev.utils.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.DevUtils;
import dev.utils.LogPrintUtils;

/**
 * detail: ADB shell 工具类
 * Created by Ttt
 * hint:
 *
 * // Awesome Adb——一份超全超详细的 ADB 用法大全
 * https://github.com/mzlogin/awesome-adb
 *
 * // Process.waitFor()的返回值含义
 * https://blog.csdn.net/qq_35661171/article/details/79096786
 *
 * // adb shell input
 * https://blog.csdn.net/soslinken/article/details/49587497
 *
 * android 上发送adb 指令，不需要加 adb shell
 *
 * // https://www.imooc.com/qadetail/198264
 * grep 是 linux 下的命令, windows 用 findstr
 *
 * 开启 Thread 执行, 非主线程, 否则无响应并无效
 */
public final class ADBUtils {

    private ADBUtils(){
    }

    // 日志 TAG
    private static final String TAG = ADBUtils.class.getSimpleName();
    // 正则 - 空格
    private static final String SPACE_STR = "\\s";
    /** 换行字符串 */
    private static final String NEW_LINE_STR = System.getProperty("line.separator");

    /**
     * 判断设备是否 root
     * @return
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = { "/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/" };
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 请求 Root 权限
     */
    public static void requestRoot(){
        ShellUtils.execCmd("exit", true);
    }

    /**
     * 判断 App 是否授权 Root 权限
     * @return
     */
    public static boolean isGrantedRoot(){
        ShellUtils.CommandResult result = ShellUtils.execCmd("exit", true);
        return result.isSuccess();
    }

    // == 应用管理 ==

    // = 应用列表 =

    /**
     * 获取 App 列表(包名)
     * @param type
     * @return
     * https://blog.csdn.net/henni_719/article/details/62222439
     */
    public static List<String> getAppList(String type){
        // adb shell pm list packages [options]
        String typeStr = isSpace(type) ? "" : " " + type;
        // 执行 shell cmd
        ShellUtils.CommandResult result = ShellUtils.execCmd("pm list packages" + typeStr, false);
        if (result.isSuccess3()){
            try {
                String[] arys = result.successMsg.split(NEW_LINE_STR);
                return Arrays.asList(arys);
            } catch (Exception e){
                LogPrintUtils.eTag(TAG, e, "getAppList type => " + typeStr);
            }
        }
        return null;
    }

    /**
     * 获取 App 安装列表(包名)
     * @return
     */
    public static List<String> getInstallAppList(){
        return getAppList(null);
    }

    /**
     * 获取用户安装的应用列表(包名)
     * @return
     */
    public static List<String> getUserAppList(){
        return getAppList("-3");
    }

    /**
     * 获取系统应用列表(包名)
     * @return
     */
    public static List<String> getSystemAppList(){
        return getAppList("-s");
    }

    /**
     * 获取启用的应用列表(包名)
     * @return
     */
    public static List<String> getEnableAppList(){
        return getAppList("-e");
    }

    /**
     * 获取禁用的应用列表(包名)
     * @return
     */
    public static List<String> getDisableAppList(){
        return getAppList("-d");
    }

    /**
     * 获取包名包含字符串 xxx 的应用列表
     * @param strFilter
     * @return
     */
    public static List<String> getAppListToFilter(String strFilter){
        if (isSpace(strFilter)) return null;
        return getAppList("| grep " + strFilter.trim());
    }

    /**
     * 判断是否安装应用
     * @param packageName
     * @return
     */
    public static boolean isInstalledApp(String packageName){
        if (isSpace(packageName)) return false;
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd("pm path " + packageName, false);
        return result.isSuccess3();
    }

    // = 安装/卸载 =

    /**
     * 安装应用
     * @param filePath /sdcard/xxx/x.apk
     * @return
     */
    public static boolean installApp(String filePath){
        return installApp("-rtsd", filePath);
    }

    /**
     * 安装应用
     * @param filePath /sdcard/xxx/x.apk
     * @return
     */
    public static boolean installApp(String params, String filePath){
        if (isSpace(params)) return false;
        boolean isRoot = isDeviceRooted();

//        -l	将应用安装到保护目录 /mnt/asec
//        -r	允许覆盖安装
//        -t	允许安装 AndroidManifest.xml 里 application 指定 android:testOnly="true" 的应用
//        -s	将应用安装到 sdcard
//        -d	允许降级覆盖安装
//        -g	授予所有运行时权限
//        android:testOnly="true"(ide 绿色三角运行) => https://blog.csdn.net/lihenhao/article/details/79146211

        // adb install [-lrtsdg] <path_to_apk>
        String cmd = "adb install %s %s";
        // 执行 shell cmd
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, params, filePath), isRoot);
        // 判断是否成功
        if (result.isSuccess4("success")){
            return true;
        }
        return false;
    }

    /**
     * 静默安装 App
     * @param filePath
     * @return
     */
    public static boolean installAppSilent(final String filePath) {
        return installAppSilent(getFileByPath(filePath));
    }

    /**
     * 静默安装 App
     * <uses-permission android:name="android.permission.INSTALL_PACKAGES" />
     * @param file The file.
     * @return true : success, false : fail
     */
    public static boolean installAppSilent(final File file) {
        if (!isFileExists(file)) return false;
        boolean isRoot = isDeviceRooted();
        String filePath = file.getAbsolutePath();
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
        ShellUtils.CommandResult result = ShellUtils.execCmd(command, isRoot);
        if (result.isSuccess4("success")) {
            return true;
        } else {
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm install " + filePath;
            result = ShellUtils.execCmd(command, isRoot, true);
            return result.isSuccess4("success");
        }
    }

    /**
     * 卸载 App
     * @param packageName
     * @return
     */
    public static boolean uninstallApp(String packageName) {
        return uninstallApp(packageName, false);
    }

    /**
     * 卸载 App
     * @param packageName
     * @param isKeepData -k 参数可选，表示卸载应用但保留数据和缓存目录。
     * @return
     */
    public static boolean uninstallApp(String packageName, boolean isKeepData) {
        if (isSpace(packageName)) return false;
        boolean isRoot = isDeviceRooted();
        // adb uninstall [-k] <packagename>
        String cmd = "adb uninstall ";
        if (isKeepData){
            cmd += " -k ";
        }
        cmd += packageName;
        // 执行 shell cmd
        ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, isRoot);
        // 判断是否成功
        if (result.isSuccess4("success")){
            return true;
        }
        return false;
    }

    /**
     * 静默卸载 App
     * @param packageName
     * @return
     */
    public static boolean uninstallAppSilent(String packageName) {
        return uninstallAppSilent(packageName, false);
    }

    /**
     * 静默卸载 App
     * @param packageName
     * @param isKeepData
     * @return
     */
    public static boolean uninstallAppSilent(String packageName, boolean isKeepData) {
        if (isSpace(packageName)) return false;
        boolean isRoot = isDeviceRooted();
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
        ShellUtils.CommandResult result = ShellUtils.execCmd(command, isRoot, true);
        if (result.isSuccess4("success")) {
            return true;
        } else {
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm uninstall " + (isKeepData ? "-k " : "") + packageName;
            result = ShellUtils.execCmd(command, isRoot, true);
            return result.isSuccess4("success");
        }
    }

    /**
     * 获取 App versionCode
     * @param packageName
     * @return
     */
    public static int getVersionCode(String packageName){
        if (isSpace(packageName)) return 0;
        try {
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd("dumpsys package " + packageName + " | grep version", true);
            if (result.isSuccess3()){
                String[] arys = result.successMsg.split(SPACE_STR);
                for (String str : arys) {
                    if (!isSpace(str)) {
                        try {
                            String[] datas = str.split("=");
                            if (datas.length == 2) {
                                if (datas[0].toLowerCase().equals("versionCode".toLowerCase())) {
                                    return Integer.parseInt(datas[1]);
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "getVersionCode");
        }
        return 0;
    }

    /**
     * 获取 App versionName
     * @param packageName
     * @return
     */
    public static String getVersionName(String packageName){
        if (isSpace(packageName)) return null;
        try {
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd("dumpsys package " + packageName + " | grep version", true);
            if (result.isSuccess3()){
                String[] arys = result.successMsg.split(SPACE_STR);
                for (String str : arys) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            String[] datas = str.split("=");
                            if (datas.length == 2) {
                                if (datas[0].toLowerCase().equals("versionName".toLowerCase())) {
                                    return datas[1];
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "getVersionName");
        }
        return null;
    }

    // ===========
    // == Input ==
    // ===========

    // = tap = 模拟touch屏幕的事件

    /**
     * 点击某个区域
     * @param x
     * @param y
     * @return
     */
    public static boolean tap(float x, float y){
        try {
            // input [touchscreen|touchpad|touchnavigation] tap <x> <y>
            // input [屏幕、触摸板、导航键] tap
            String cmd = "input touchscreen tap %s %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, (int) x, (int) y), true);
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "tap");
        }
        return false;
    }

    // = swipe = 滑动事件

    /**
     * 按压某个区域(点击)
     * @param x
     * @param y
     * @return
     */
    public static boolean swipeClick(float x, float y){
        return swipe(x, y, x, y, 100l);
    }

    /**
     * 按压某个区域 time 大于一定时间变成长按
     * @param x
     * @param y
     * @param time 按压时间
     * @return
     */
    public static boolean swipeClick(float x, float y, long time){
        return swipe(x, y, x, y, time);
    }

    /**
     * 滑动到某个区域
     * @param x
     * @param y
     * @param tX
     * @param tY
     * @param time 滑动时间(毫秒)
     * @return
     */
    public static boolean swipe(float x, float y, float tX, float tY, long time){
        try {
            // input [touchscreen|touchpad|touchnavigation] swipe <x1> <y1> <x2> <y2> [duration(ms)]
            String cmd = "input touchscreen swipe %s %s %s %s %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, (int) x, (int) y, (int) tX, (int) tY, time), true);
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "swipe");
        }
        return false;
    }

    // = text = 模拟输入

    /**
     * 输入文本 - 不支持中文
     * @param txt
     * @return
     */
    public static boolean text(String txt){
        if (isSpace(txt)) return false;
        try {
            // input text <string>
            String cmd = "input text %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, txt), true); // false 可以执行
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "text");
        }
        return false;
    }

    // = keyevent = 按键操作

    /**
     * 触发某些按键
     * @param keyCode  KeyEvent.xxx => KeyEvent.KEYCODE_BACK(返回键)
     * @return
     */
    public static boolean keyevent(int keyCode){
        try {
            // input keyevent <key code number or name>
            String cmd = "input keyevent %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, keyCode), true); // false 可以执行
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "keyevent");
        }
        return false;
    }

    // =============
    // == dumpsys ==
    // =============

    /**
     * 获取对应包名应用启动 Activity
     * android.intent.category.LAUNCHER (android.intent.action.MAIN)
     * @param packageName
     * @return
     */
    public static String getActivityToLauncher(String packageName){
        if (isSpace(packageName)) return null;
        String cmd = "dumpsys package %s";
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, packageName), true);
        if (result.isSuccess3()){
            String mainStr = "android.intent.action.MAIN:";
            int start = result.successMsg.indexOf(mainStr);
            // 防止都为null
            if (start != -1){
                try {
                    // 进行裁减字符串
                    String subData = result.successMsg.substring(start + mainStr.length(), result.successMsg.length());
                    // 进行拆分
                    String[] arys = subData.split(NEW_LINE_STR);
                    for (String str : arys){
                        if (!TextUtils.isEmpty(str)){
                            // 存在包名才处理
                            if (str.indexOf(packageName) != -1){
                                String[] splitArys = str.split(SPACE_STR);
                                for (String strData : splitArys){
                                    if (!TextUtils.isEmpty(strData)){
                                        // 属于 包名/ 前缀的
                                        if (strData.indexOf(packageName + "/") != -1){
                                            // 防止属于 包名/.xx.Main_Activity
                                            if (strData.indexOf("/.") != -1){
                                                // 包名/.xx.Main_Activity => 包名/包名.xx.Main_Activity
                                                strData = strData.replace("/", "/" + packageName);
                                            }
                                            return strData;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e){
                    LogPrintUtils.eTag(TAG, e, "getActivityToLauncher " + packageName);
                }
            }
        }
        return null;
    }

    // == 获取当前Window ==

    /**
     * 获取当前显示的 Window
     * adb shell dumpsys window -h
     * @return
     */
    public static String getWindowCurrent(){
        String cmd = "dumpsys window w | grep \\/  |  grep name=";
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, true);
        if (result.isSuccess3()){
            try {
                String nameStr = "name=";
                String[] arys = result.successMsg.split(NEW_LINE_STR);
                for (String str : arys){
                    if (!TextUtils.isEmpty(str)){
                        int start = str.indexOf(nameStr);
                        if (start != -1){
                            try {
                                String subData = str.substring(start + nameStr.length());
                                if (subData.indexOf(")") != -1){
                                    return subData.substring(0, subData.length() - 1);
                                }
                                return subData;
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getWindowCurrent");
            }
        }
        return null;
    }

    /**
     * 获取当前显示的 Window
     * @return
     */
    public static String getWindowCurrent2(){
        String cmd = "dumpsys window windows | grep Current";
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, true);
        if (result.isSuccess3()){
            try {
                // 拆分换行, 并循环
                String[] arys = result.successMsg.split(NEW_LINE_STR);
                for (String str : arys){
                    if (!TextUtils.isEmpty(str)){
                        String[] splitArys = str.split(SPACE_STR);
                        if (splitArys != null && splitArys.length != 0){
                            for (String splitStr : splitArys){
                                if (!TextUtils.isEmpty(splitStr)){
                                    int start = splitStr.indexOf("/");
                                    int lastIndex = splitStr.lastIndexOf("}");
                                    if (start != -1 && lastIndex != -1){
                                        // 获取裁减数据
                                        String strData = splitStr.substring(0, lastIndex);
                                        // 防止属于 包名/.xx.Main_Activity
                                        if (strData.indexOf("/.") != -1){
                                            // 包名/.xx.Main_Activity => 包名/包名.xx.Main_Activity
                                            strData = strData.replace("/", "/" + splitStr.substring(0, start));
                                        }
                                        return strData;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getWindowCurrent2");
            }
        }
        return null;
    }

    /**
     * 获取对应包名 显示的 Window
     * @param packageName
     * @return
     */
    public static String getWindowCurrentToPackage(String packageName){
        if (isSpace(packageName)) return null;
        String cmd = "dumpsys window windows | grep %s";
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, packageName), true);
        if (result.isSuccess3()) {
            try {
                // 拆分换行, 并循环
                String[] arys = result.successMsg.split(NEW_LINE_STR);
                for (String str : arys){
                    if (!TextUtils.isEmpty(str)){
                        String[] splitArys = str.split(SPACE_STR);
                        if (splitArys != null && splitArys.length != 0){
                            for (String splitStr : splitArys){
                                if (!TextUtils.isEmpty(splitStr)){
                                    int start = splitStr.indexOf("/");
                                    int lastIndex = splitStr.lastIndexOf("}");
                                    if (start != -1 && lastIndex != -1 && splitStr.indexOf(packageName) == 0){
                                        // 获取裁减数据
                                        String strData = splitStr.substring(0, lastIndex);
                                        // 防止属于 包名/.xx.Main_Activity
                                        if (strData.indexOf("/.") != -1){
                                            // 包名/.xx.Main_Activity => 包名/包名.xx.Main_Activity
                                            strData = strData.replace("/", "/" + packageName);
                                        }
                                        return strData;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getWindowCurrentToPackage");
            }
        }
        return null;
    }

    // == 获取当前Activity ==

    /**
     * 获取当前显示的 Activity
     * @return
     */
    public static String getActivityCurrent(){
        String cmd = "dumpsys activity activities | grep mFocusedActivity";
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            cmd = "dumpsys activity activities | grep mResumedActivity";
        }
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, true);
        if (result.isSuccess3()) {
            try {
                // 拆分换行, 并循环
                String[] arys = result.successMsg.split(NEW_LINE_STR);
                for (String str : arys){
                    if (!TextUtils.isEmpty(str)){
                        String[] splitArys = str.split(SPACE_STR);
                        if (splitArys != null && splitArys.length != 0){
                            for (String splitStr : splitArys){
                                if (!TextUtils.isEmpty(splitStr)){
                                    int start = splitStr.indexOf("/");
                                    if (start != -1){
                                        // 获取裁减数据
                                        String strData = splitStr;
                                        // 防止属于 包名/.xx.Main_Activity
                                        if (strData.indexOf("/.") != -1){
                                            // 包名/.xx.Main_Activity => 包名/包名.xx.Main_Activity
                                            strData = strData.replace("/", "/" + splitStr.substring(0, start));
                                        }
                                        return strData;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getActivityCurrent");
            }
        }
        return null;
    }

    // ========
    // == am ==
    // ========

    /**
     * 跳转页面 Activity
     * @param packageAndLauncher
     * @param closeActivity 关闭Activity所属的App进程后再启动Activity
     * @return
     */
    public static boolean startActivity(String packageAndLauncher, boolean closeActivity){
        if (isSpace(packageAndLauncher)) return false;
        try {
            // am start [options] <INTENT>
            String cmd = "am start %s";
            if (closeActivity){
                cmd = String.format(cmd, "-S " + packageAndLauncher);
            } else {
                cmd = String.format(cmd, packageAndLauncher);
            }
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, true);
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "startActivity");
        }
        return false;
    }

    /**
     * 销毁进程
     * @param packageName
     * @return
     */
    public static boolean kill(String packageName){
        if (isSpace(packageName)) return false;
        try {
            String cmd = "am force-stop %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, packageName), true);
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "kill");
        }
        return false;
    }

    // == 实用功能 ==

    /**
     * 屏幕截图
     * @param path /sdcard/xxx/x.png
     * @return
     */
    public static boolean screencap(String path){
        return screencap(path, 0);
    }

    /**
     * 屏幕截图
     * @param path /sdcard/xxx/x.png
     * @param displayId -d display-id	指定截图的显示屏编号（有多显示屏的情况下）默认0
     * @return
     */
    public static boolean screencap(String path, int displayId){
        if (isSpace(path)) return false;
        try {
            String cmd = "screencap -p -d %s %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, Math.max(displayId, 0), path), true);
            return result.isSuccess2();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "screencap");
        }
        return false;
    }

    /**
     * 录制屏幕 (以 mp4 格式保存到 /sdcard)
     * @param path
     * @return
     */
    public static boolean screenrecord(String path){
        return screenrecord(path, null, -1, -1);
    }

    /**
     * 录制屏幕 (以 mp4 格式保存到 /sdcard)
     * @param path
     * @param time
     * @return
     */
    public static boolean screenrecord(String path, int time){
        return screenrecord(path, null, -1, time);
    }

    /**
     * 录制屏幕 (以 mp4 格式保存到 /sdcard)
     * @param path
     * @param size
     * @param time
     * @return
     */
    public static boolean screenrecord(String path, String size, int time){
        return screenrecord(path, size, -1, time);
    }

    /**
     * 录制屏幕 (以 mp4 格式保存到 /sdcard)
     * @param path /sdcard/xxx/x.mp4
     * @param size 视频的尺寸，比如 1280x720，默认是屏幕分辨率。
     * @param bitRate 视频的比特率，默认是 4Mbps。
     * @param time 录制时长，单位秒。(默认/最长 180秒)
     * @return
     */
    public static boolean screenrecord(String path, String size, int bitRate, int time){
        if (isSpace(path)) return false;
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("screenrecord");
            if (!isSpace(size)){
                buffer.append(" --size " + size);
            }
            if (bitRate > 0){
                buffer.append(" --bit-rate " + bitRate);
            }
            if (time > 0){
                buffer.append(" --time-limit " + time);
            }
            buffer.append(" " + path);
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(buffer.toString(), true);
            return result.isSuccess2();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "screenrecord");
        }
        return false;
    }

    /**
     * 查看连接过的 WiFi 密码
     * @return
     */
    public static String wifiConf(){
        try {
            String cmd = "cat /data/misc/wifi/*.conf";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(cmd, true);
            if (result.isSuccess()){
                return result.successMsg;
            }
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "wifiConf");
        }
        return null;
    }

    /**
     * 开启/关闭 WiFi
     * @param open
     * @return
     */
    public static boolean wifiSwitch(boolean open){
        String cmd = "svc wifi %s";
        // 执行 shell
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, open ? "enable" : "disable"), true);
        return result.isSuccess();
    }

    /**
     * 设置系统时间
     * @param time 20160823.131500
     *             表示将系统日期和时间更改为 2016 年 08 月 23 日 13 点 15 分 00 秒。
     * @return
     */
    public static boolean setSystemTime(String time){
        if (isSpace(time)) return false;
        try {
            String cmd = "date -s %s";
            // 执行 shell
            ShellUtils.CommandResult result = ShellUtils.execCmd(String.format(cmd, time), true);
            return result.isSuccess();
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "setSystemTime");
        }
        return false;
    }

    // = 刷机相关命令 =

    /**
     * 关机 (需要 root 权限)
     * @return
     */
    public static boolean shutdown() {
        try {
            ShellUtils.execCmd("reboot -p", true);
            Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
            intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
            DevUtils.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "shutdown");
        }
        return false;
    }

    /**
     * 重启设备 (需要 root 权限)
     * @return
     */
    public static boolean reboot() {
        try {
            ShellUtils.execCmd("reboot", true);
            Intent intent = new Intent(Intent.ACTION_REBOOT);
            intent.putExtra("nowait", 1);
            intent.putExtra("interval", 1);
            intent.putExtra("window", 0);
            DevUtils.getContext().sendBroadcast(intent);
            return true;
        } catch (Exception e){
            LogPrintUtils.eTag(TAG, e, "reboot");
        }
        return false;
    }

    /**
     * 重启设备 (需要 root 权限) - 并进行特殊的引导模式 (recovery、 Fastboot)
     * @param reason 传递给内核来请求特殊的引导模式，如"recovery"
     *               重启到 Fastboot 模式 bootloader
     */
    public static void reboot(String reason) {
        try {
            PowerManager mPowerManager = (PowerManager) DevUtils.getContext().getSystemService(Context.POWER_SERVICE);
            if (mPowerManager == null)
                return;
            mPowerManager.reboot(reason);
        } catch (Exception e) {
            LogPrintUtils.eTag(TAG, e, "reboot");
        }
    }

    /**
     * 重启引导到 recovery (需要 root 权限)
     * @return
     */
    public static boolean reboot2Recovery() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("reboot recovery", true);
        return result.isSuccess2();
    }

    /**
     * 重启引导到 bootloader (需要 root 权限)
     * @return
     */
    public static boolean reboot2Bootloader() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("reboot bootloader", true);
        return result.isSuccess2();
    }

    // == 滑动方法 ==

    /**
     * 发送事件滑动
     * @param x
     * @param y
     * @param tX
     * @param tY
     * @param number 循环次数
     * @return
     */
    public static void sendEventSlide(float x, float y, float tX, float tY, int number){
        List<String> lists = new ArrayList<>();
        // = 开头 =
        lists.add("sendevent /dev/input/event1 3 57 109");
        lists.add("sendevent /dev/input/event1 3 53 " + x);
        lists.add("sendevent /dev/input/event1 3 54 " + y);
        // 发送touch 事件(必须使用0 0 0配对)
        lists.add("sendevent /dev/input/event1 1 330 1");
        lists.add("sendevent /dev/input/event1 0 0 0");

        // 判断方向(手势是否从左到右) - View 往左滑, 手势操作往右滑
        boolean isLeftToRight = tX > x;
        // 判断方向(手势是否从上到下) - View 往上滑, 手势操作往下滑
        boolean isTopToBottom = tY > y;

        // 计算差数
        float diffX = isLeftToRight ? (tX - x) : (x - tX);
        float diffY = isTopToBottom ? (tY - y) : (y - tY);

        if (!isLeftToRight){
            diffX = -diffX;
        }

        if (!isTopToBottom){
            diffY = -diffY;
        }

        // 平均值
        float averageX = diffX / number;
        float averageY = diffY / number;
        // 上次位置
        int oldX = (int) x;
        int oldY = (int) y;

        // 循环处理
        for (int i = 0; i <= number; i++){
            if (averageX != 0f){
                // 进行判断处理
                int calcX = (int) (x + averageX * i);
                if (oldX != calcX) {
                    oldX = calcX;
                    lists.add("sendevent /dev/input/event1 3 53 " + calcX);
                }
            }

            if (averageY != 0f){
                // 进行判断处理
                int calcY = (int) (y + averageY * i);
                if (oldY != calcY) {
                    oldY = calcY;
                    lists.add("sendevent /dev/input/event1 3 54 " + calcY);
                }
            }
            // 每次操作结束发送
            lists.add("sendevent /dev/input/event1 0 0 0");
        }
        // = 结尾 =
        lists.add("sendevent /dev/input/event1 3 57 4294967295");
        // 释放touch事件（必须使用0 0 0配对）
        lists.add("sendevent /dev/input/event1 1 330 0");
        lists.add("sendevent /dev/input/event1 0 0 0");

        // 执行 Shell
        ShellUtils.execCmd(lists, true);
    }

    // ==

    /**
     * 检查是否存在某个文件
     * @param file 文件路径
     * @return 是否存在文件
     */
    private static boolean isFileExists(File file){
        return file != null && file.exists();
    }

    /**
     * 获取文件
     * @param filePath
     * @return
     */
    private static File getFileByPath(String filePath){
        return filePath != null ? new File(filePath) : null;
    }

    /**
     * 判断字符串是否为 null 或全为空白字符
     * @param str 待校验字符串
     * @return
     */
    private static boolean isSpace(String str) {
        if (str == null) return true;
        for (int i = 0, len = str.length(); i < len; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
