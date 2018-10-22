package com.github.mag0716.api

import com.github.mag0716.api.model.Data
import com.github.mag0716.api.model.Detail
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/data")
    fun data(): Deferred<List<Data>>

    @GET("/data/{id}")
    fun detail(@Path("id") id: Int): Deferred<Detail>
}