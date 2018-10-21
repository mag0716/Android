package com.github.mag0716.dependencysample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mag0716.librarya.ClassA
import com.github.mag0716.libraryb.ClassB
import com.github.mag0716.libraryd.ClassD

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // implementation
    // :libraryc を修正したら、librarya のみ再ビルドが必要
    fun greetFromA() = ClassA().greeting()
    // cannot compile
    //fun greetFromC() = ClassC().hello()

    // api
    // :libraryd を修正したら、libraryb と app も再ビルドが必要
    fun greetFromB() = ClassB().greeting()

    fun greetFromD() = ClassD().hello()
}
