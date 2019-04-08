package com.github.mag0716.libraryb

import com.github.mag0716.libraryd.ClassD

class ClassB {
    private val provider = ClassD()
    fun greeting() = provider.hello()
}