package com.s.mvpmodel

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.s.mvpmodel.delegate.AppDelegate

/**
 * Created by Administrator on 2019/3/7.
 */
class App : Application() {

    private val appDelegate: AppDelegate by lazy(LazyThreadSafetyMode.NONE) {
        AppDelegate(this)
    }

    companion object {
        lateinit var app: App
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appDelegate.onCreate()
    }
}
