package com.github.mag0716.appwidgetsample

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.core.content.edit
import com.github.mag0716.appwidgetsample.NewAppWidget.Companion.COUNT_KEY
import com.github.mag0716.appwidgetsample.NewAppWidget.Companion.SHARED_PREFERENCES_FILE
import java.text.DateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    companion object {
        private const val TAG = "AppWidgetSample"
        const val SHARED_PREFERENCES_FILE = "appwidgetsample"
        const val COUNT_KEY = "count"
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d(TAG, "onUpdate : $appWidgetIds")
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        Log.d(TAG, "onEnabled")
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Log.d(TAG, "onDisabled")
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    views.setTextViewText(R.id.appwidget_id, appWidgetId.toString())

    val prefs = context.getSharedPreferences(
        SHARED_PREFERENCES_FILE,
        Context.MODE_PRIVATE
    )
    val count = prefs.getInt(COUNT_KEY + appWidgetId, 0)
    val newCount = count.inc()
    val dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date())
    views.setTextViewText(
        R.id.appwidget_update,
        context.resources.getString(
            R.string.date_count_format,
            newCount,
            dateString
        )
    )

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)

    prefs.edit {
        putInt(COUNT_KEY + appWidgetId, count)
    }
}