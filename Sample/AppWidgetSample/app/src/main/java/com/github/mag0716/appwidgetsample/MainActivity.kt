package com.github.mag0716.appwidgetsample

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AppWidget"
    }

    private lateinit var checkBox: CheckBox
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        checkBox = findViewById(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            (application as App).repository.isEnabled = isChecked
        }
        button = findViewById(R.id.button)
        button.setOnClickListener {
            updateWidgets()
        }
    }

    private fun updateWidgets() {
        Log.d(TAG, "updateWidgets")
        val context = applicationContext
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(
                        context,
                        ListAppWidget::class.java
                )
        )
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list)
    }
}