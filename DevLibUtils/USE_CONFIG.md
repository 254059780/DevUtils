
## 初始化

```java
/**
 * detail: 全局Application
 * Created by Ttt
 */
public class BaseApplication extends Application{

    // 日志TAG
    private final String LOG_TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化工具类
        DevUtils.init(this.getApplicationContext());
        // == 初始化日志配置 ==
        // 设置默认Logger配置
        LogConfig logConfig = new LogConfig();
        logConfig.logLevel = LogLevel.DEBUG;
        logConfig.tag = LOG_TAG;
        DevLogger.init(logConfig);
        // 打开 lib 内部日志
        DevUtils.openLog();
        DevUtils.openDebug();
    }
}
```

# 配置使用相关 - [目录](https://github.com/afkT/DevUtils/tree/master/app/src/main/java/com/dev/use)


## 日志配置 - [DevLogger](https://github.com/afkT/DevUtils/tree/master/app/src/main/java/com/dev/use/logger)

> [LoggerUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/logger/LoggerUse.java) 介绍了配置参数及使用

```java
/**
 * detail: 日志使用方法
 * Created by Ttt
 */
class LoggerUse {

    private LoggerUse() {
    }

    /** 日志Tag */
    private final static String LOG_TAG = LoggerUse.class.getSimpleName();
    /** 日志文件夹路径 */
    public static final String LOG_SD_PATH = Config.SDP_PATH + File.separator + "Logger" + File.separator;

    // ================
    // ===== 配置 =====
    // ================

    /**
     * 日志配置相关
     */
    private void logConfig(){
        // == 在BaseApplication 中调用 ==
        // 初始化日志配置
        LogConfig lConfig = new LogConfig();
        // 堆栈方法总数(显示经过的方法)
        lConfig.methodCount = 3;
        // 堆栈方法索引偏移(0 = 最新经过调用的方法信息,偏移则往上推,如 1 = 倒数第二条经过调用的方法信息)
        lConfig.methodOffset = 0;
        // 是否输出全部方法(在特殊情况下，如想要打印全部经过的方法，但是不知道经过的总数)
        lConfig.isOutputMethodAll = false;
        // 显示日志线程信息(特殊情况，显示经过的线程信息,具体情况如上)
        lConfig.isDisplayThreadInfo = false;
        // 是否排序日志(格式化后)
        lConfig.isSortLog = false;
        // 日志级别
        lConfig.logLevel = LogLevel.DEBUG;
        // 设置Tag（特殊情况使用，不使用全部的Tag时,如单独输出在某个Tag下）
        lConfig.tag = "BaseLog";
        // 进行初始化配置 => 这样设置后, 默认全部日志都使用改配置, 特殊使用 DevLogger.other(config).d(xxx);
        DevLogger.init(lConfig);
//        // 进行初始化配置 - 必须调用 => 在DevUtils.init() 内部调用了
//        DevLoggerUtils.appInit(mContext);
    }

    // === 使用 ===

    /**
     * 日志使用方法
     */
    public static void loggerUse() {
        // 测试打印Log所用时间
        textTime();

        // try, catch 保存异常日志
        exLog();

        // 正常保存日志
        saveLog();

        // 使用日志操作
        tempLog();
    }
}
```


## 自定义Toast (Toasty) 配置 - [Toasty](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/toast/cus/Toasty.java)

> [ToastyUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/toasty/ToastyUse.java) 介绍了配置参数及使用

```java
/**
 * detail: Toasty 使用方法
 * Created by Ttt
 */
class ToastyUse {

    /**
     * 配置相关
     */
    private void config(){
        Toasty.Config config = Toasty.Config.getInstance();
        // 设置异常、错误颜色 - 红色
        config.setErrorColor(Color.parseColor("#D50000"));
        // 设置打印信息颜色 - 海洋蓝
        config.setInfoColor(Color.parseColor("#3F51B5"));
        // 设置成功颜色 - 绿色
        config.setSuccessColor(Color.parseColor("#388E3C"));
        // 设置警告颜色 - 橙色
        config.setWarningColor(Color.parseColor("#FFA900"));
        // ==
        // 设置字体样式
        config.setToastTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        // 设置字体大小 sp
        config.setTextSize(16);
        // 是否渲染图标
        config.setTintIcon(true);
        // 是否使用新的Toast
        config.setNewToast(true);
        // 应用配置
        config.apply();
    }

    /**
     * 使用
     */
    private void use(){
//        // 显示成功 Toast
//        Toasty.success(mActivity, "Success!", Toast.LENGTH_SHORT, true);
//        // 显示异常 Toast
//        Toasty.error(mActivity, "Error!", Toast.LENGTH_SHORT, true);
//        // 显示信息 Toast
//        Toasty.info(mActivity, "Info!", Toast.LENGTH_SHORT, true);
//        // 显示警告 Toast
//        Toasty.warning(mActivity, "Warning!", Toast.LENGTH_SHORT, true);
//        // 显示默认 Toast (灰色)
//        Toasty.normal(mActivity, "Normal!", Toast.LENGTH_SHORT);
//
//        // -- 使用自定义配置 --
//        Toasty.custom(mActivity, "自定义Toast", ToastyUtils.getDrawable(DevUtils.getContext(), R.mipmap.ic_launcher), Color.RED, Toast.LENGTH_LONG, true);
//
//        Toasty.custom(mActivity, "自定义Toast", ToastyUtils.getDrawable(DevUtils.getContext(), R.mipmap.ic_launcher), Color.RED, Toast.LENGTH_LONG, true, false);
//
//        // -- 或者获取 Toast View 进行自定义 --
//
//        // 引入View
//        final View toastLayout = ((LayoutInflater) DevUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(dev.utils.R.layout.dev_toast_layout, null);
//        // 初始化View
//        final ImageView toastIcon = (ImageView) toastLayout.findViewById(dev.utils.R.id.vid_dtl_toast_igview);
//        final TextView toastTextView = (TextView) toastLayout.findViewById(dev.utils.R.id.vid_dtl_toast_tv);
//
//        // 设置背景色
//        // ToastyUtils.setBackground(toastLayout, xxx);
//        // 设置字体色
//        // toastTextView.setTextColor(xxx);
//        // toastTextView.setTextColor(Toasty.Config.getInstance().getErrorColor());
//        // 是否显示图标
//        // ViewUtils.setVisibility(false, toastIcon);
//
//        // 可以通过 Toasty.Config.getInstance().getXxx 获取配置的参数
//
//        // 显示Toast
//        Toasty.showToasty(mActivity, toastLayout, duration, isNewToast);
    }
}
```


## 定时器工具类 使用 - [TimerManager](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/assist/manager/TimerManager.java)

> [TimerUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/timer/TimerUse.java) 介绍了配置参数及使用

```java
/**
 * detail: 定时器使用方法
 * Created by Ttt
 */
class TimerUse {

    /** 日志Tag */
    private final static String TAG = TimerUse.class.getSimpleName();
    // 创建定时器
    TimerManager.AbsTimer absTimer;
    /** 通知常量 */
    private final int NOTIFY = 100;

    /**
     * 定时器使用方法
     */
    private void timerUse(){

//        /** 创建定时器 => 立即执行,无限循环,通知默认what */
//        public static TimerManager.AbsTimer creTimer(Handler handler, long period) {
//            return creTimer(handler, TimerManager.AbsTimer.TIMER_NOTIFY_WHAT, 0l, period, -1);
//        }
//
//        /** 创建定时器 => 无限循环,通知默认what */
//        public static TimerManager.AbsTimer creTimer(Handler handler, long delay, long period) {
//            return creTimer(handler, TimerManager.AbsTimer.TIMER_NOTIFY_WHAT, delay, period, -1);
//        }
//
//        /** 创建定时器 => 立即执行,通知默认what */
//        public static TimerManager.AbsTimer creTimer(Handler handler, long period, int triggerLimit) {
//            return creTimer(handler, TimerManager.AbsTimer.TIMER_NOTIFY_WHAT, 0l, period, triggerLimit);
//        }
//
//        /** 创建定时器 => 立即执行,无限循环 */
//        public static TimerManager.AbsTimer creTimer(Handler handler, int what, long period) {
//            return creTimer(handler, what, 0l, period, -1);
//        }
//
//        /** 创建定时器 => 无限循环 */
//        public static TimerManager.AbsTimer creTimer(Handler handler, int what, long delay, long period) {
//            return creTimer(handler, what, delay, period, -1);
//        }
//
//        /** 创建定时器 => 立即执行 */
//        public static TimerManager.AbsTimer creTimer(Handler handler, int what, long period, int triggerLimit) {
//            return creTimer(handler, what, 0l, period, triggerLimit);
//        }
//
//        /**
//         * 创建定时器
//         * @param handler 通知的Handler
//         * @param what 通知的what
//         * @param delay 延迟时间 - 多少毫秒后开始执行
//         * @param period 循环时间 - 每隔多少秒执行一次
//         * @param triggerLimit 触发次数上限(-1,表示无限循环)
//         * @return
//         */
//        public static TimerManager.AbsTimer creTimer(Handler handler, int what, long delay, long period, int triggerLimit) {
//            return new TimerManager.TimerTask(handler, what, delay, period, triggerLimit);
//        }

        // 初始化定时器任务
        absTimer = TimerManager.creTimer(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                // 获取触发次数
                int number = absTimer.getTriggerNumber();
                // 触发次数
                if (number == 1){
                    DevLogger.dTag(TAG, "第一次触发, 0.5秒延迟");
                } else {
                    DevLogger.dTag(TAG, "每隔2秒触发一次, 触发次数: " + number);
                }
            }
        }, NOTIFY, 500l, 2000l, -1);
        // 开始定时
        absTimer.startTimer();


//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                switch (msg.what){
//                    case NOTIFY:
//                        // 获取触发次数
//                        DevLogger.dTag(TAG, "已经触发次数：" + absTimer.getTriggerNumber());
//                        DevLogger.dTag(TAG, "触发上限次数：" + absTimer.getTriggerLimit());
//                        // 判断是否触发结束
//                        if (absTimer.isTriggerEnd()){
//                            DevLogger.dTag(TAG, "触发结束");
//                        }
//                        break;
//                }
//            }
//        };
//
//        // 配置参数 - 意思是 一开始0秒直接触发第一次，然后后面每隔60秒触发一次，通过Handler通知 NOTIFY 常量 (-1表示无限次)
//        absTimer.setTriggerLimit(-1).setTime(0, 60 * 1000).setNotifyWhat(NOTIFY);
//
//        // 配置参数 - 一秒钟后进行触发,然后每隔1秒循环触发(但是触发一次 TriggerLimit 限制了次数), 并通过设置的Handler通知 对应传入的What
//        absTimer.setHandler(handler).setTriggerLimit(1).setTime(1000, 1000).setNotifyWhat(NOTIFY);
//
//        // 配置参数 - 3秒钟后进行触发,然后每隔3秒循环触发(但是触发10次 TriggerLimit 限制了次数), 并通过设置的Handler通知 对应传入的What,并且开始定时器
//        absTimer.setHandler(handler).setTriggerLimit(10).setTime(3000, 3000).setNotifyWhat(NOTIFY).startTimer();
//
//        // 开始运行定时器
//        absTimer.startTimer();
//
//        // 关闭定时器
//        absTimer.closeTimer();
//
//        // 判断是否运行中
//        absTimer.isRunTimer();
//
//        /** 关闭所有符合对应的标记id的定时器任务 */
//        TimerManager.closeMark(id);
//
//        /** 关闭所有符合对应的字符串标记的定时器任务 */
//        TimerManager.closeMark("mark");
//
//        /** 关闭所有无限循环的任务 */
//        TimerManager.closeInfiniteTask();
//
//        /** 关闭全部任务 */
//        TimerManager.closeAll();
//
//        /** 回收资源 - 回收需要回收的 */
//        TimerManager.gc();
    }
}
```


## 多媒体工具类 使用 - [DevMediaManager](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/player/DevMediaManager.java)

> [MediaUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/media/MediaUse.java) 介绍了配置参数及使用

```java
/**
 * detail: 多媒体使用方法
 * Created by Ttt
 */
class MediaUse {

    /**
     * 多媒体使用方法
     */
    private void mediaUse(){
        // 默认事件监听
        DevMediaManager.getInstance().setMeidaListener(new DevMediaManager.MediaListener() {
            @Override
            public void onPrepared() {
                DevMediaManager.getInstance().getMediaPlayer().start();
            }

            @Override
            public void onCompletion() {
            }

            @Override
            public void onBufferingUpdate(int percent) {
            }

            @Override
            public void onSeekComplete() {
            }

            @Override
            public void onError(int what, int extra) {
            }

            @Override
            public void onVideoSizeChanged(int width, int height) {
            }
        });

        // =======

        // 播放音频
        DevMediaManager.getInstance().playPrepareRaw(R.raw.dev_beep);
        DevMediaManager.getInstance().playPrepareAssets("a.mp3");
        DevMediaManager.getInstance().playPrepare(SDCardUtils.getSDCardPath() + "/a.mp3");
        DevMediaManager.getInstance().playPrepare("http://xxx.mp3");
        DevMediaManager.getInstance().playPrepare(new DevMediaManager.MediaSet(){

            @Override
            public void setMediaConfig(MediaPlayer mediaPlayer) throws Exception {
                mediaPlayer.setDataSource("xxx");
            }
        }); // 自由设置信息

        // =======

        SurfaceView surfaceView = null;
        // 播放视频
        DevVideoPlayerControl control = new DevVideoPlayerControl(surfaceView);
        control.startPlayer(SDCardUtils.getSDCardPath() + "/video_3.mp4");
        control.startPlayer("http://xxx.mp4");
        control.startPlayer(new DevMediaManager.MediaSet(){
            @Override
            public void setMediaConfig(MediaPlayer mediaPlayer) throws Exception {
                mediaPlayer.setDataSource("xxx");
            }
        }); // 自由设置信息
    }
}
```


## 线程工具类 使用 - [DevThreadManager](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/common/thread/DevThreadManager.java)

> [ThreadUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/thread/ThreadUse.java) 介绍了配置参数及使用

```java
/**
 * detail: 线程使用方法
 * Created by Ttt
 */
class ThreadUse {

    /**
     * 线程使用方法
     */
    private void threadUse(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        // == 优先判断 10个线程数, 的线程池是否存在, 不存在则创建, 存在则复用 ==
        DevThreadManager.getInstance(10).execute(runnable);

        // 与上面 传入 int 是完全不同的线程池
        DevThreadManager.getInstance("10").execute(runnable);

        // 可以先增加配置
        DevThreadManager.putConfig("QPQP", new DevThreadPool(DevThreadPool.DevThreadPoolType.CALC_CPU));
        // 使用配置的信息
        DevThreadManager.getInstance("QPQP").execute(runnable);


        DevThreadManager.putConfig("QQQQQQ", 10);
        // 使用配置的信息
        DevThreadManager.getInstance("QQQQQQ").execute(runnable);


        // 另外一个线程管理工具类, 单独使用简化版 DevThreadPool
        ThreadManager.getInstance().addTask(runnable);
    }
}
```


## Wifi热点工具类 使用 - [WifiHotUtils](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/wifi/WifiHotUtils.java)

> [WifiHotUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/wifi/WifiHotUse.java) 介绍了配置参数及使用

```java
/**
 * detail: Wifi热点使用方法
 * Created by Ttt
 */
class WifiHotUse {

    /**
     * Wifi热点使用方法
     */
    private void wifiHotUse(){

//        // 需要权限
//        <uses-permission android:name="android.permission.WRITE_SETTINGS" />
//        <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
//        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
//        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
//        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
//        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

        final WifiHotUtils wifiHotUtils = new WifiHotUtils(DevUtils.getContext());

        // 有密码
        WifiConfiguration wifiConfiguration = WifiHotUtils.createWifiConfigToAp("WifiHot_AP", "123456789");

        // 无密码
        wifiConfiguration = WifiHotUtils.createWifiConfigToAp("WifiHot_AP", null);

        // 开启热点(兼容8.0)  7.1 跳转到热点页面, 需手动开启(但是配置信息使用上面的 WifiConfig)
        wifiHotUtils.stratWifiAp(wifiConfiguration);

        // 关闭热点
        wifiHotUtils.closeWifiAp();

        // === 8.0 特殊处理 ===

        // 8.0 以后热点是针对应用开启, 并且必须强制使用随机生成的 WifiConfig 信息, 无法替换

        // 如果应用开启了热点, 然后后台清空内存, 对应的热点会关闭, 应用开启的热点是系统随机的，不影响系统设置中的热点配置信息

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            wifiHotUtils.setOnWifiAPListener(new WifiHotUtils.onWifiAPListener() {
                @Override
                public void onStarted(WifiConfiguration wifiConfig) {
                    String ssid = wifiHotUtils.getApWifiSSID();
                    String pwd = wifiHotUtils.getApWifiPwd();
                }

                @Override
                public void onStopped() {

                }

                @Override
                public void onFailed(int reason) {

                }
            });
        }

        // 还有其他方法, 具体看 WifiHotUtils 类
    }
}
```


## 日志、异常文件记录保存工具类 使用 - [FileRecordUtils](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/FileRecordUtils.java)

> [FileRecordUse](https://github.com/afkT/DevUtils/blob/master/app/src/main/java/com/dev/use/record/FileRecordUse.java) 介绍了配置参数及使用

```java
/**
 * detail: 日志、异常文件记录保存使用方法
 * Created by Ttt
 */
class FileRecordUse {

    // 日志TAG
    private static final String TAG = FileRecordUse.class.getSimpleName();

    /**
     * 日志、异常文件记录保存使用方法
     */
    private void fileRecordUse() {
        // AnalysisRecordUtils

        // DevLoggerUtils

        // FileRecordUtils

        // DevLoggerUtils => 内部的 Utils, 实际和 FileRecordUtils 代码相同, 使用方式一致

        // == 记录文件 ==

        // AnalysisRecordUtils 工具类使用方法
        analysisRecord();

        // DevLoggerUtils、FileRecordUtils 工具类
        logRecord();
    }

    /** 日志文件夹路径 */
    public static final String LOG_SD_PATH = Config.SDP_PATH + File.separator + "Logger" + File.separator;

    /**
     * AnalysisRecordUtils 工具类使用方法
     */
    private void analysisRecord(){

        // 默认存储到 android/data/包名/cache文件/ , 可以自己特殊设置
//        AnalysisRecordUtils.setLogStoragePath(SDCardUtils.getSDCardPath());

        // 设置存储文件夹名
        AnalysisRecordUtils.setLogFolderName(AnalysisRecordUtils.getLogFolderName() + "/v" + AppUtils.getAppVersionName());

        // AnalysisRecordUtils.HH、MM、SS => 以对应的时间, 创建文件夹 HH_23/MM_13/SS_01 依此类推，放到对应文件夹, 不传则放到当日文件夹下
        AnalysisRecordUtils.FileInfo fileInfo = AnalysisRecordUtils.FileInfo.obtain("test_log.txt", "测试记录", AnalysisRecordUtils.HH);

//        // 存储路径、存储文件夹、文件名、记录功能提示、时间间隔
//        // FileInfo(String storagePath, String folderName, String fileName, String fileFunction, @AnalysisRecordUtils.TIME int fileIntervalTime)
//
//        // ==
//
//        AnalysisRecordUtils.FileInfo.obtain("test_log.txt", "测试记录");
//
//        AnalysisRecordUtils.FileInfo.obtain("TempRecord","test_log.txt", "测试记录");
//
//        AnalysisRecordUtils.FileInfo.obtain(SDCardUtils.getSDCardPath(),"TempRecord","test_log.txt", "测试记录");
//
//        // ==
//
//        AnalysisRecordUtils.FileInfo.obtain("test_log.txt", "测试记录", AnalysisRecordUtils.HH);
//
//        AnalysisRecordUtils.FileInfo.obtain("TempRecord","test_log.txt", "测试记录", AnalysisRecordUtils.MM);
//
//        AnalysisRecordUtils.FileInfo.obtain(SDCardUtils.getSDCardPath(),"TempRecord","test_log.txt", "测试记录", AnalysisRecordUtils.SS);

        // =============================

        // 存储到 android/data/包名/LogFolderName/2018-08-23/LogFolderName/xxx/log.txt
        AnalysisRecordUtils.record(fileInfo, "急哦撒点娇阿什库大街阿奎罗圣诞节几点佛山的金佛i适当放宽就是可怜的");

        // 存储到 sdcard/LogFolderName/2018-08-23/SDRecord/xxx/log.txt
        AnalysisRecordUtils.record(AnalysisRecordUtils.FileInfo.obtain(SDCardUtils.getSDCardPath(),"SDRecord","sd_log.txt", "根目录保存", AnalysisRecordUtils.HH),
                "奇葩奇葩奇葩撒开排名第");

        // 存储到 sdcard/特殊地址/LogFolderName/2018-08-23/OtherRecord/xxx/log.txt
        AnalysisRecordUtils.record(AnalysisRecordUtils.FileInfo.obtain(SDCardUtils.getSDCardPath() + "/特殊地址","OtherRecord","log.txt", "临时地址", AnalysisRecordUtils.HH),
                "手机发的啥地方加快速度加快");

        // =

        // 保存错误信息
        NullPointerException nullPointerException = new NullPointerException("报错啦， null 异常啊");
        // 记录日志
        AnalysisRecordUtils.record(fileInfo, ErrorUtils.getThrowableMsg(nullPointerException));
    }

    /**
     * DevLoggerUtils、FileRecordUtils 工具类
     */
    private void logRecord(){
        // = 异常日志保存 =

        try {
            String s = null;
            s.indexOf("c");
        } catch (NullPointerException e) {
            // 保存的路径
            String fName = LOG_SD_PATH + System.currentTimeMillis() + ".log";
            // 保存日志信息
            DevLoggerUtils.saveErrorLog(e, fName, true);
            // --
            // 保存自定义头部、底部信息
            DevLoggerUtils.saveErrorLog(e, "头部", "底部", LOG_SD_PATH, System.currentTimeMillis() + "_存在头部_底部.log", true);
            // --
            // 自定义(无设备信息、失败信息获取失败) - 正常不会出现，所以其实这个可以不用
            String[] eHint = new String[]{"DeviceInfo = 获取设备信息失败", "获取失败"};
            // 保存的路径
            fName = LOG_SD_PATH + System.currentTimeMillis() + "_orgs.log";
            // 保存日志信息
            DevLoggerUtils.saveErrorLog(e, fName, true, eHint);

            // ==  FileRecordUtils 使用方法 ==

            FileRecordUtils.saveErrorLog(e, "头部", "底部", LOG_SD_PATH, System.currentTimeMillis() + "_存在头部_底部.log", true, true, "xaskdjaslkd");

            FileRecordUtils.saveLog("撒开多久阿什利", "头部", "底部", LOG_SD_PATH, System.currentTimeMillis() + "_存在头部_底部.log", true, "qqqqweqweqwe");
        }
    }

}
```