package com.github.mag0716.jsonparsersample.model.kotshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mag0716 on 2018/02/09.
 */
class DateConverter {

    private val FORMAT = "yyyyMMddHHmmss"

    @ToJson
    fun toJson(date: Date): String {
        return ""
    }

    @FromJson
    fun fromJson(date: String): Date {
        try {
            return SimpleDateFormat(FORMAT, Locale.getDefault()).parse(date)
        } catch (e: ParseException) {
            throw JsonDataException("illegal datetime format : $date")
        }
    }
}