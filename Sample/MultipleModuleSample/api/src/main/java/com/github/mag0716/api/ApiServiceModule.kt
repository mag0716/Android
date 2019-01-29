package com.github.mag0716.api

import com.github.mag0716.api.response.DataResponse
import com.github.mag0716.api.response.DetailResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import timber.log.Timber
import timber.log.debug

object ApiServiceModule {

    fun provide(): ApiService {
        return object : ApiService {
            override fun data() = GlobalScope.async {
                Timber.debug { "data" }
                return@async createData()
            }

            override fun detail(id: Int) = GlobalScope.async {
                Timber.debug { "detail($id)" }
                return@async createDetail(id)
            }
        }
    }

    private suspend fun createData(): List<DataResponse> {
        delay(1000)
        return (0..10).map {
            DataResponse(it, "title$it")
        }.toList()
    }

    private suspend fun createDetail(id: Int): DetailResponse {
        delay(1000)
        return DetailResponse(
            id,
            "title$id",
            "description$id",
            createdTime = System.currentTimeMillis()
        )
    }
}