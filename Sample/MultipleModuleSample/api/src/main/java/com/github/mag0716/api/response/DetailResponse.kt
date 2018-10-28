package com.github.mag0716.api.response

data class DetailResponse(
        val id: Int,
        val title: String,
        val description: String,
        val image: String? = null,
        val createdTime: Long
)