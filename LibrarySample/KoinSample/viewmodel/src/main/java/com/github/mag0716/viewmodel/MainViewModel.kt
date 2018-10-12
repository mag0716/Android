package com.github.mag0716.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel(val helloService: HelloService) : ViewModel() {

    val hello = helloService.hello()
}