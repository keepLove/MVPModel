package com.s.mvpmodel.ui.main

import android.os.Bundle
import com.s.mvpmodel.R
import com.s.mvpmodel.ui.BaseActivity

/**
 * Created by Administrator on 2019/3/7.
 */
class MainActivity : BaseActivity() {

    override fun getLayoutResID(): Int? {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
    }
}
