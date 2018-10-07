package com.github.mag0716.constructorinjection

class ProductPresenter(val helloService: HelloService) : Presenter {

    override fun requestHello() = helloService.hello()
}