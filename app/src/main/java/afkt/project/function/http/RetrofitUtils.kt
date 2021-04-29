package afkt.project.function.http

import dev.DevUtils
import dev.environment.DevEnvironment
import dev.other.http.HttpLoggingInterceptor
import dev.other.retrofit.RetrofitManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * detail: Retrofit 工具类
 * @author Ttt
 */
class RetrofitUtils private constructor() {

    companion object {

        val instance: RetrofitUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtils()
        }

        // 日志 TAG
        private val TAG = RetrofitUtils::class.java.simpleName
    }

    // =============
    // = 初始化方法 =
    // =============

    /**
     * 初始化 Retrofit 配置
     * @return [RetrofitUtils]
     */
    fun initRetrofit(): RetrofitUtils {
        // 初始化 [WanAndroidService] 配置
        initWanAndroidService()
        return this
    }

    /**
     * 重置 API Service
     * 修改了 BaseUrl, 对应的 API Service 需要使用新的 Retrofit create
     */
    fun resetAPIService(): RetrofitUtils {
        resetWanAndroidService()
        return this
    }

    // =====================
    // = WanAndroidService =
    // =====================

    /**
     * 初始化 [WanAndroidService] 配置
     */
    private fun initWanAndroidService() {

        // ====================
        // = OkHttpClient 配置 =
        // ====================

        val builder = OkHttpClient.Builder()
        // 自定义日志拦截 JSON 打印
        builder.addInterceptor(HttpLoggingInterceptor())
        // 全局的读取超时时间
        builder.readTimeout(60000L, TimeUnit.MILLISECONDS)
        // 全局的写入超时时间
        builder.writeTimeout(60000L, TimeUnit.MILLISECONDS)
        // 全局的连接超时时间
        builder.connectTimeout(60000L, TimeUnit.MILLISECONDS)

        // ================
        // = Retrofit 配置 =
        // ================

        val retrofit = Retrofit.Builder()
            // Gson 解析
            .addConverterFactory(GsonConverterFactory.create())
            // RxJava3 适配器
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            // OkHttpClient
            .client(builder.build())
            // 服务器地址
            .baseUrl(DevEnvironment.getServiceEnvironmentValue(DevUtils.getContext()))
            .build()

        // =======================
        // = 存入 RetrofitManager =
        // =======================

        RetrofitManager.getInstance().put(WAN_ANDROID_SERVICE_TAG, retrofit)
    }

    @Volatile
    private var wanAndroidService: WanAndroidService? = null

    // WanAndroidService RetrofitManager TAG
    private val WAN_ANDROID_SERVICE_TAG = WanAndroidService::class.java.simpleName

    fun wanAndroidService(): WanAndroidService {
        if (wanAndroidService == null) {
            synchronized(WanAndroidService::class.java) {
                if (wanAndroidService == null) {
                    wanAndroidService = RetrofitManager.getInstance().create(
                        WAN_ANDROID_SERVICE_TAG, WanAndroidService::class.java
                    )
                }
            }
        }
        return wanAndroidService as WanAndroidService
    }

    /**
     * 重置 WanAndroidService Service
     * 修改了 BaseUrl, 对应的 API Service 需要使用新的 Retrofit create
     */
    fun resetWanAndroidService(): RetrofitUtils {
        initWanAndroidService()
        wanAndroidService = null
        return this
    }
}