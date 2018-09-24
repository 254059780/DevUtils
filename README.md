# About

> DevUtils 是一个 Android 工具库, 主要根据不同功能模块，封装快捷使用的工具类及 API 方法调用。
> <p>该项目尽可能的便于开发人员，快捷、快速开发安全可靠的项目，以及内置部分常用的资源文件，如color.xml、(toast) layout.xml等

# Gradle

Step 1. Add the JitPack repository to your build file
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency
```
dependencies {
	// 因为内含 res 文件, 使用 aar 方式调用
	implementation 'com.github.afkT:DevUtils:1.0.4@aar'
	// implementation 'com.github.afkT:DevUtils:latest.release@aar'
}
```

## Documentation

- [README - API](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/README.md)

- [Use Config - API](https://github.com/afkT/DevUtils/blob/master/DevLibUtils/USE_CONFIG.md)


## Use

> 只需要在 Application 中调用 DevUtils.init() 进行初始化就行
> <p>DevUtils.openLog() 是打开内部工具类 日志输出, 发包则不调用此句
> <p> DevLogger => https://github.com/afkT/DevLogger

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

## Other

> [DevQuickUtils](https://github.com/afkT/DevQuickUtils) 是一个 基于 [DevUtils](https://github.com/afkT/DevUtils) 二次封装的快捷开发实现库, 封装多数逻辑判断优化实体类、如 基类Activity、Fragment、Adapter、ReqInfoAssist(请求信息辅助类)、PageInfoAssist(分页辅助类)、MultiSelectListAssist、MultiSelectMapAssist(多选辅助类) 等 便于开发人员，基于 [DevUtils](https://github.com/afkT/DevUtils)、[DevQuickUtils](https://github.com/afkT/DevQuickUtils) 快速开发 Android 项目

# Thanks

> 感谢以下开源项目的作者，本项目中有些功能受你们项目灵感的启发，有些功能也用到你们的代码完成。

- [orhanobut/logger](https://github.com/orhanobut/logger)
- [laobie/StatusBarUtil](https://github.com/laobie/StatusBarUtil)
- [GrenderG/Toasty](https://github.com/GrenderG/Toasty)
- [Blankj/AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
- [l123456789jy/Lazy](https://github.com/l123456789jy/Lazy)
- [yangfuhai/ASimpleCache](https://github.com/yangfuhai/ASimpleCache)
- [AbrahamCaiJin/CommonUtilLibrary](https://github.com/AbrahamCaiJin/CommonUtilLibrary)
- [litesuits/android-common](https://github.com/litesuits/android-common)
