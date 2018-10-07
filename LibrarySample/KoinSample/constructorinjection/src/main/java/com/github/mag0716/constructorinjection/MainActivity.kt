package com.github.mag0716.constructorinjection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ConstructorInjection"
    }

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "${presenter.requestHello()}($presenter)")

        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
