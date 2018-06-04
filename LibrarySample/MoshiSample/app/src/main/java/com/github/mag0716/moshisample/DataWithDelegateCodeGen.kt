package com.github.mag0716.moshisample

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataWithDelegateCodeGen(val name: String) : Common by CommonImpl() {
    companion object
}