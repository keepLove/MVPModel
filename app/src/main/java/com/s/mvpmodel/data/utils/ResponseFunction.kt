package com.s.mvpmodel.data.utils

import com.s.mvpmodel.data.model.BaseResponse
import io.reactivex.ObservableSource

/**
 * Created by Administrator on 2019/3/8.
 * 返回码统一处理
 */
class ResponseFunction<T : BaseResponse> : io.reactivex.functions.Function<T, ObservableSource<T>> {

    override fun apply(t: T): io.reactivex.ObservableSource<T> {
        return when (t.code) {
            200 -> io.reactivex.Observable.just(t)
            else -> io.reactivex.Observable.error(ResponseException(t.msg))
        }
    }

}