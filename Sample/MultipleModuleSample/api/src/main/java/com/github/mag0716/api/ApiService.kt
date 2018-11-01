package com.github.mag0716.api

import com.github.mag0716.api.response.DataResponse
import com.github.mag0716.api.response.DetailResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/data")
    fun data(): Deferred<List<DataResponse>>

    @GET("/data/{id}")
    fun detail(@Path("id") id: Int): Deferred<DetailResponse>
}