package com.github.mag0716.api

import com.github.mag0716.api.model.Data
import com.github.mag0716.api.model.Detail
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class ApiClientFactory() {

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
            // TODO: GlobalScope を使って本当に大丈夫なのか？
            override fun data() = GlobalScope.async {
                return@async createData()
            }


            override fun detail(id: Int) = GlobalScope.async {
                return@async createDetail(id)
            }
        }
    }

    private suspend fun createData(): List<Data> {
        delay(1000)
        return (0..10).map {
            Data(it, "title$it")
        }.toList()
    }

    private suspend fun createDetail(id: Int): Detail {
        delay(1000)
        return Detail(
                id,
                "title$id",
                "description$id",
                createdTime = System.currentTimeMillis())
    }
}