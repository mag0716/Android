package com.github.mag0716.api.model

data class Detail(
        val id: Int,
        val title: String,
        val description: String,
        val image: String?,
        val createdTime: Long
)