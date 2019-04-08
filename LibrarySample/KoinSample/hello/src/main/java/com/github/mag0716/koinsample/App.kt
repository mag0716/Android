package com.github.mag0716.koinsample

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class App : Application() {

    private val appModule = module {
        single<HelloService> { ProductHelloService() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}