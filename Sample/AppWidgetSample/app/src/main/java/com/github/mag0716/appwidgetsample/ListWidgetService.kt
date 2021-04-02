package com.github.mag0716.appwidgetsample

import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService

class ListWidgetService : RemoteViewsService() {

    companion object {
        private const val TAG = "RemoteViewsService"
    }

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        Log.d(TAG, "onGetViewFactory")
        return ListWidgetRemoteViewsFactory(applicationContext)
    }
}