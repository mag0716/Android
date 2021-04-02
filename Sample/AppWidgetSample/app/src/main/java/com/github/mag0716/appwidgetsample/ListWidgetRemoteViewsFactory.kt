package com.github.mag0716.appwidgetsample

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class ListWidgetRemoteViewsFactory(
    private val context: Context,
    val intent: Intent
) : RemoteViewsService.RemoteViewsFactory {

    companion object {
        private const val TAG = "RemoteViewsFactory"
    }

    private val titleList = mutableListOf<String>()

    override fun onCreate() {
        Log.d(TAG, "onCreate")
    }

    override fun onDataSetChanged() {
        Log.d(TAG, "onDataSetChanged")
        val list = fetchData()
        titleList.clear()
        titleList.addAll(list)
    }

    override fun onDestroy() {
        titleList.clear()
    }

    override fun getCount(): Int {
        Log.d(TAG, "getCount : ${titleList.size}")
        return titleList.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.list_item)
        remoteViews.setTextViewText(R.id.title, titleList[position])
        return remoteViews
    }

    override fun getLoadingView() = null

    override fun getViewTypeCount() = 1

    override fun getItemId(position: Int) = position.toLong()

    override fun hasStableIds() = true

    private fun fetchData(): List<String> {
        Thread.sleep(5000)

        val currentTimeMillis = System.currentTimeMillis()

        val list = mutableListOf<String>()
        for (i in 0..10) {
            list.add("Title$i($currentTimeMillis)")
        }
        return list
    }
}