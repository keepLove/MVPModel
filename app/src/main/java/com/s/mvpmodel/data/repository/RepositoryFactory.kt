package com.s.mvpmodel.data.repository

import com.s.mvpmodel.data.repository.api.TestRepositoryApi

/**
 * Created by Administrator on 2019/3/7.
 */
object RepositoryFactory {

    val testRepositoryApi: TestRepositoryApi
        get() = TestRepository()

    val testRepositoryApiInstance: TestRepositoryApi by lazy { TestRepository() }
}