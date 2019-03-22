package com.s.mvpmodel.data.repository.api

import com.s.mvpmodel.data.model.test.TestResponse
import io.reactivex.Observable

interface TestRepositoryApi {

    /**
     * get
     */
    fun testGet(params: Map<String, String>): Observable<TestResponse>

    /**
     * post
     */
    fun testPost(params: Map<String, String>): Observable<TestResponse>
}