package com.s.mvpmodel.data.net

import com.s.mvpmodel.BuildConfig
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

/**
 * Created by Administrator on 2019/3/7.
 */
object NetCore {

    /**
     * 连接超时时间 秒
     */
    private const val CONNECT_TIMEOUT = 5L
    /**
     * 读取超时时间 秒
     */
    private const val IO_TIMEOUT = 5L
    /**
     * 缓存大小
     */
    private const val NET_CACHE_SIZE = (1024 * 1024 * 10).toLong()
    /**
     * build url
     */
    var buildUrl = NetConfig.BASE_URL
    /**
     *  okHttpClient
     */
    var okHttpClient: okhttp3.OkHttpClient? = null
    /**
     * Retrofit
     */
    val retrofit: retrofit2.Retrofit by lazy {
        retrofit2.Retrofit.Builder()
                .baseUrl(NetCore.buildUrl)
                .client(NetCore.okHttpClient!!)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
    /**
     * token
     */
    var token: String = ""
    /**
     * User-Agent
     */
    val userAgent: String by lazy {
        "Android-${BuildConfig.FLAVOR}/${BuildConfig.VERSION_CODE}"
    }

    fun init(context: android.content.Context) {
        val level = if (BuildConfig.DEBUG) {
            okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        } else {
            okhttp3.logging.HttpLoggingInterceptor.Level.NONE
        }
        val builder = okhttp3.OkHttpClient.Builder()
                .connectTimeout(NetCore.CONNECT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(NetCore.IO_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(NetCore.IO_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .addNetworkInterceptor {
                    var request = it.request()
                    request = request.newBuilder()
                            .header("User-Agent", NetCore.userAgent)
                            .header("token", NetCore.token)
                            .build()
                    it.proceed(request)
                }
                .cache(okhttp3.Cache(context.cacheDir, NET_CACHE_SIZE))
                // 域名验证
//                .hostnameVerifier { hostname, _ ->
//                     NetCore.buildUrl.contains(hostname)
//                }
                .addInterceptor(okhttp3.logging.HttpLoggingInterceptor().setLevel(level))
        NetCore.okHttpClient = builder.build()
    }

}

/**
 * 得到service
 */
fun <T> Class<T>.getService(): T {
    return NetCore.retrofit.create(this)
}

/**
 * 准备上传的文件
 *
 * @param partName 参数名
 * @return MultipartBody.Part
 */
fun java.io.File.getImageFilePart(partName: String = "image"): okhttp3.MultipartBody.Part {
    val requestFile = okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/*"), this)
    return okhttp3.MultipartBody.Part.createFormData(partName, this.name, requestFile)
}

/**
 * 准备上传的参数 参数值
 *
 * @param partName 参数名
 * @return MultipartBody.Part
 */
fun String.getPart(partName: String): okhttp3.MultipartBody.Part {
    return okhttp3.MultipartBody.Part.createFormData(partName, this)
}
