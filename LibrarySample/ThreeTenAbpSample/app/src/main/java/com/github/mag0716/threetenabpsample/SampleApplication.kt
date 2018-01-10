package com.github.mag0716.threetenabpsample

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}