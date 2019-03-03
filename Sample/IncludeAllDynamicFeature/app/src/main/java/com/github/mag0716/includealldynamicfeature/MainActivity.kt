package com.github.mag0716.includealldynamicfeature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setClassName(
                packageName,
                "com.github.mag0716.includealldynamicfeature.feature.FeatureActivity"
            )
            startActivity(intent)
        }
    }

}
