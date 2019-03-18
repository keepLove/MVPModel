package com.s.mvpmodel.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2019/3/18.
 */
abstract class BaseFragment : Fragment() {

    var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResID(), container)
            init(savedInstanceState)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getLayoutResID(): Int

    abstract fun init(savedInstanceState: Bundle?)
}