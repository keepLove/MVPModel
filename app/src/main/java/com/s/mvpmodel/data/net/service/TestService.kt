package com.s.mvpmodel.data.net.service

import com.s.mvpmodel.data.model.test.TestResponse
import com.s.mvpmodel.data.net.NetConfig
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 测试service
 */
interface TestService {

    /**
     * get
     */
    @GET(NetConfig.TEST_URL)
    fun testGet(@QueryMap params: Map<String, String>): Observable<TestResponse>

    /**
     * post
     */
    @FormUrlEncoded
    @POST(NetConfig.TEST_URL)
    fun testPost(@FieldMap params: Map<String, String>): Observable<TestResponse>
}