package com.github.mag0716.api

import com.github.mag0716.api.model.Data
import com.github.mag0716.api.model.Detail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // TODO: coroutine で返す(最悪でも Rx で)
    @GET("/data")
    fun data(): Call<List<Data>>

    @GET("/data/{id}")
    fun detail(@Path("id") id: Int): Call<Detail>
}