package com.github.mag0716.moshisample

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataWithImplCodeGen(val name: String, override val id: Int) : Common {
    companion object
}