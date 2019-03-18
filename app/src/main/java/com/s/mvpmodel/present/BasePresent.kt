package com.s.mvpmodel.present

/**
 * Created by Administrator on 2019/3/7.
 */
interface BasePresent<T : BaseView> {

    fun setView(view: T)

    fun start()
}