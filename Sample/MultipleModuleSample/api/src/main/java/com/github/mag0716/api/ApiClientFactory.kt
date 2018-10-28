package com.github.mag0716.api

import com.github.mag0716.api.response.DataResponse
import com.github.mag0716.api.response.DetailResponse
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class ApiClientFactory(val coroutineScope: CoroutineScope) {

//    fun create(): ApiService {
//        return Retrofit.Builder()
//                // TODO: flavor 毎に定義を変えられるようにする
//                .baseUrl("Http://example.com")
//                .client(OkHttpClient())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//    }

    fun create(): ApiService {
        return object : ApiService {
            override fun data() = coroutineScope.async {
                return@async createData()
            }


            override fun detail(id: Int) = coroutineScope.async {
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
                createdTime = System.currentTimeMillis())
    }
}