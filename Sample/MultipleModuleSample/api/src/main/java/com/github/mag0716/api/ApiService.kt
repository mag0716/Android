package com.github.mag0716.api

import com.github.mag0716.api.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    // TODO: coroutine で返す(最悪でも Rx で)
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repository>>
}