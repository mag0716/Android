package com.github.mag0716.api.response

data class DataResponse(
        val id: Int,
        val title: String,
        val thumbnail: String? = null
)