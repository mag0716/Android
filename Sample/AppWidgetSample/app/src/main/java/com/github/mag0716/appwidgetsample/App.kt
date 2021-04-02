package com.github.mag0716.appwidgetsample

import android.app.Application

class App : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        repository = Repository()
    }
}