package com.github.mag0716.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClientFactory {

    fun create(): ApiService {
        return Retrofit.Builder()
                // TODO: flavor 毎に定義を変えられるようにする
                .baseUrl("Http://example.com")
                .client(OkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}