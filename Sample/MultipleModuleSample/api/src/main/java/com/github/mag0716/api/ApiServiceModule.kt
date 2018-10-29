package com.github.mag0716.api

import kotlinx.coroutines.experimental.CoroutineScope

object ApiServiceModule {
    fun inject(coroutineScope: CoroutineScope): ApiService {
        return ApiClientFactory(coroutineScope).create()
    }
}