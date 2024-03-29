package com.s.mvpmodel.ui

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by Administrator on 2019/3/18.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutResID()?.let {
            setContentView(it)
        }
        init(savedInstanceState)
    }

    abstract fun getLayoutResID(): Int?

    abstract fun init(savedInstanceState: Bundle?)
}