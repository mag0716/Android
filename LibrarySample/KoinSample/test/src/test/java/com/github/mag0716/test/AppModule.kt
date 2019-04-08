package com.github.mag0716.test

import org.koin.dsl.module.module

class AppModule {

    companion object {
        val appModule = module {
            single<HelloService> { TestHelloService() }
        }
    }
}