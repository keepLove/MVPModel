package com.s.mvpmodel.data.utils

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Administrator on 2019/3/8.
 * 返回数据统一处理
 */
open class ResponseObserver<T> : Observer<T> {

    override fun onError(e: Throwable) {
        if (!showError(e)) {
            when (e) {
                is ResponseException -> {
                    showMessage(e.message)
                }
                else -> {
                    showMessage("网络异常")
                }
            }
        }
    }

    /**
     * 拦截错误
     *
     * @return 是否处理这个错误
     */
    open fun showError(e: Throwable): Boolean {
        return false
    }

    open fun showMessage(message: String?) {

    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onComplete() {
    }
}