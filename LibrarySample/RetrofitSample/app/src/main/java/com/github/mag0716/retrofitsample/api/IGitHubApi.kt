package com.github.mag0716.retrofitsample.api

import com.github.mag0716.retrofitsample.api.response.User
import retrofit2.http.GET

interface IGitHubApi {
    @GET("users/mag0716")
    suspend fun user(): User
}