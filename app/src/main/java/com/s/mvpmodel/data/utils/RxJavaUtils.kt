package com.s.mvpmodel.data.utils

import com.s.mvpmodel.data.model.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2019/3/8.
 */

/**
 * 获取运行在子线程.结束在主线程的转换器
 */
fun <T> Observable<T>.composeToMainSchedulerTransform(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * 网络响应的转换器,调用在子线程,结束在主线程.并对响应状态进行处理
 */
fun <T : BaseResponse> Observable<T>.composeToMainSchedulerResponseTransform(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
                .flatMap(ResponseFunction<T>())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * 网络响应的转换器,调用在子线程,结束在主线程.并对响应状态进行处理
 */
fun <T : BaseResponse> Observable<T>.composeToThreadSchedulerResponseTransform(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
                .flatMap(ResponseFunction<T>())
    }
}

/**
 * 对异常统一处理
 */
fun <T : Any> Observable<T>.subscribeResponseObserver(onError: (message: String?) -> Unit = {},
                                                      onSubscribe: (d: Disposable?) -> Unit = {}, onComplete: () -> Unit = {},
                                                      showError: (e: Throwable) -> Boolean = { false },
                                                      onNext: (t: T) -> Unit = {}) {
    subscribe(object : ResponseObserver<T>() {
        override fun onNext(t: T) {
            onNext.invoke(t)
        }

        override fun showMessage(message: String?) {
            onError.invoke(message)
        }

        override fun showError(e: Throwable): Boolean {
            return showError.invoke(e)
        }

        override fun onComplete() {
            onComplete.invoke()
        }

        override fun onSubscribe(d: Disposable) {
            onSubscribe.invoke(d)
        }
    })
}