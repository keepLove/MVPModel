package com.s.mvpmodel.delegate

import android.app.Application
import com.s.mvpmodel.common.utils.LogUtils
import com.s.mvpmodel.common.utils.logE
import com.s.mvpmodel.data.net.NetCore
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by Administrator on 2019/3/18.
 */
class AppDelegate(private val application: Application) {

    private val activityDelegate: ActivityDelegate by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDelegate()
    }
    private var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    /**
     * 初始化操作
     */
    fun onCreate() {
        NetCore.init(application)
        LogUtils.init(application)
        application.registerActivityLifecycleCallbacks(activityDelegate)
        crashHandler()
    }

    /**
     * 异常处理
     */
    private fun crashHandler() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val sw = StringWriter()
                val pw = PrintWriter(sw)
                throwable.printStackTrace(pw)
                var stackTraceString = sw.toString()
                if (stackTraceString.length > MAX_STACK_TRACE_SIZE) {
                    val disclaimer = " [stack trace too large]"
                    stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length) + disclaimer
                }
                logE("crash:$stackTraceString")
                defaultUncaughtExceptionHandler?.uncaughtException(thread, throwable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        /**
         * 最大log大小
         */
        const val MAX_STACK_TRACE_SIZE = 131071
    }
}