package com.s.mvpmodel.ui.main

import android.os.Bundle
import com.s.mvpmodel.R
import com.s.mvpmodel.common.utils.logD
import com.s.mvpmodel.common.utils.logE
import com.s.mvpmodel.data.repository.RepositoryFactory
import com.s.mvpmodel.data.repository.api.TestRepositoryApi
import com.s.mvpmodel.data.utils.subscribeResponseObserver
import com.s.mvpmodel.ui.BaseActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Created by Administrator on 2019/3/7.
 */
class MainActivity : BaseActivity() {

    private val testRepositoryApi: TestRepositoryApi by lazy(LazyThreadSafetyMode.NONE) { RepositoryFactory.testRepositoryApi }

    override fun getLayoutResID(): Int? {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        testRepositoryApi.testGet(mapOf())
                .bindToLifecycle(this)
                .subscribeResponseObserver {
                    logD("onNext:$it")
                }
        testRepositoryApi.testPost(mapOf())
                .compose(bindToLifecycle())
                .subscribeResponseObserver(onNext = {
                    logD("onNext:$it")
                }, onError = {
                    logE("onError:$it")
                })
    }
}
