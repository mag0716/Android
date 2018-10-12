package com.github.mag0716.scope

import android.util.Log

class Presenter(val helloService: HelloService) {

    fun requestHello(): String {
        Log.d(MainActivity.TAG, "$helloService")
        return helloService.hello()
    }
}