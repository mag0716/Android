package com.github.mag0716.appwidgetsample

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
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
    private lateinit var button1: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        checkBox = findViewById(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            (application as App).repository.isEnabled = isChecked
        }
        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            updateWidgets()
        }
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            sendBroadcast()
        }
    }

    // 複数回呼ぶとホームランチャーに遷移したタイミングで1度だけonDataSetChangedが呼ばれる
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

    // 複数回呼ぶと初回はonDataSetChangedが呼ばれる
    // 2回目以降はonUpdateまでは呼ばれるがonDataSetChangedは呼ばれない
    private fun sendBroadcast() {
        Log.d(TAG, "sendBroadcast")
        val context = applicationContext
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(
                context,
                ListAppWidget::class.java
            )
        )
        val intent = Intent().apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        }
        sendBroadcast(intent)
    }
}