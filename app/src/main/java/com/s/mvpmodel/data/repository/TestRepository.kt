package com.s.mvpmodel.data.repository

import com.s.mvpmodel.data.model.test.TestResponse
import com.s.mvpmodel.data.net.getService
import com.s.mvpmodel.data.net.service.TestService
import com.s.mvpmodel.data.repository.api.TestRepositoryApi
import com.s.mvpmodel.data.utils.composeToMainSchedulerResponseTransform
import com.s.mvpmodel.data.utils.composeToThreadSchedulerResponseTransform
import io.reactivex.Observable

internal class TestRepository : TestRepositoryApi {

    private val testService = TestService::class.java.getService()

    override fun testGet(params: Map<String, String>): Observable<TestResponse> {
        return testService.testGet(params).composeToMainSchedulerResponseTransform()
    }

    override fun testPost(params: Map<String, String>): Observable<TestResponse> {
        return testService.testPost(params).composeToThreadSchedulerResponseTransform()
    }
}