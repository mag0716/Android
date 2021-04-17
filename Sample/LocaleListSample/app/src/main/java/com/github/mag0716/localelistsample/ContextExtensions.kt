package com.github.mag0716.localelistsample

import android.content.Context
import android.util.Log

fun Context.debugLocaleList() {
    Log.d("xxx", "${getStackTraceInfo()} : ${this.resources.configuration.locales}")
}

private fun getStackTraceInfo(): String {
    val element = Thread.currentThread().stackTrace[5]
    val fullName = element.className
    val className = fullName.substring(fullName.lastIndexOf(".") + 1)
    val methodName = element.methodName
    return "$className#$methodName"
}