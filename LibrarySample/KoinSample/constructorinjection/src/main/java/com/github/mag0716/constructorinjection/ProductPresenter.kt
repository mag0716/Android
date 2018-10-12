package com.github.mag0716.constructorinjection

import android.util.Log

class ProductPresenter(val helloService: HelloService) : Presenter {

    override fun requestHello(): String {
        Log.d(MainActivity.TAG, "$helloService")
        return helloService.hello()
    }
}