package com.github.mag0716.jsonparsersample.model.kotshi

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.util.*

/**
 * Created by mag0716 on 2018/02/07.
 */
@JsonSerializable
data class Data(
        val id: Int,
        val text: String,
        val nullableText: String?,
        @Json(name = "custom_name")
        val customName: String,
        val datetime: Date
)