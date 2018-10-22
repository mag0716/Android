package com.github.mag0716.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClientFactory {

    fun create(): ApiService {
        return Retrofit.Builder()
                // TODO: flavor 毎に定義を変えられるようにする
                .baseUrl("Http://example.com")
                .client(OkHttpClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}