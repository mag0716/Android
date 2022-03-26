package com.github.mag0716.ktorsample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.mag0716.ktorsample.api.ApiClient

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)

        findViewById<Button>(R.id.button).setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val apiClient = ApiClient()
                val repositoryList = apiClient.repos("mag0716")
                textView.text = repositoryList.toString()
            }
        }
    }
}