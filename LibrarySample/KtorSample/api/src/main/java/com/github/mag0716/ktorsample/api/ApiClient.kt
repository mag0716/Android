package com.github.mag0716.ktorsample.api

import com.github.mag0716.ktorsample.api.response.Repository
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiClient {
    suspend fun repos(userName: String): List<Repository> = withContext(Dispatchers.IO) {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    json = kotlinx.serialization.json.Json {
                        isLenient = false
                        ignoreUnknownKeys = true
                        allowSpecialFloatingPointValues = true
                        useArrayPolymorphism = false
                    }
                )
            }
        }.use { client ->
            client.get("https://api.github.com/users/$userName/repos")
        }
    }
}