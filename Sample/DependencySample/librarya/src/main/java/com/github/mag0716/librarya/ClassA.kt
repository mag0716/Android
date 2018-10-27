package com.github.mag0716.librarya

import com.github.mag0716.libraryc.ClassC

class ClassA {
    private val provider = ClassC()
    fun greeting() = provider.hello()
}