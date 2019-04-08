package com.github.mag0716.scope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Scope"
    }

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = getOrCreateScope("Scope")
        bindScope(scope)

        Log.d(TAG, "${presenter.requestHello()}($presenter)")

        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
