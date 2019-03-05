package com.github.mag0716.api

import retrofit2.Retrofit

object ApiServiceModule {

    fun provide(): ApiService {
        // FIXME: 動作する API サーバの定義への対応
        // base url をどこで定義するのか迷うところだが、
        // API 側の仕様を全て別モジュールにしたいので、api モジュール側で定義する
        // テスト用に増えた時などは面倒かも
        return Retrofit.Builder()
            .baseUrl("https://example.com/")
            .build()
            .create(ApiService::class.java)
    }
}