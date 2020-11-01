package com.github.mag0716.multipledevicesupportsample.ui.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

open class LoggingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this::class.java}#onCreate ${savedInstanceState == null}")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("${this::class.java}#onResume")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("${this::class.java}#onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("${this::class.java}#onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("${this::class.java}#onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${this::class.java}#onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("${this::class.java}#onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("${this::class.java}#onRestoreInstanceState")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("${this::class.java}#onConfigurationChanged $newConfig")
    }
}