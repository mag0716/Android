package com.github.mag0716.retrofitsample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mag0716.retrofitsample.api.IGitHubApi
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val gitHubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(IGitHubApi::class.java)

    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            runBlocking {
                try {
                    val user = gitHubApi.user()
                    textView.text = "success : $user"
                } catch (e: Exception) {
                    textView.text = "failed : $e"
                }
            }
        }
    }
}
