package com.github.mag0716.appwidgetsample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Repository {

    var isEnabled = false

    suspend fun fetchTitleList(): List<String> = withContext(Dispatchers.IO) {
        delay(5000)

        if (isEnabled) {
            val list = mutableListOf<String>()
            val currentTimeMillis = System.currentTimeMillis()
            for (i in 0..10) {
                list.add("Title$i($currentTimeMillis)")
            }
            list
        } else {
            emptyList<String>()
        }
    }
}