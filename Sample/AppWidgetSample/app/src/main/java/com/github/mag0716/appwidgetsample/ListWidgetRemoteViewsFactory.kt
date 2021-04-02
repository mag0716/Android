package com.github.mag0716.appwidgetsample

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import kotlinx.coroutines.runBlocking

class ListWidgetRemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {

    companion object {
        private const val TAG = "RemoteViewsFactory"
    }

    private val titleList = mutableListOf<String>()
    private lateinit var repository: Repository

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        val application = context.applicationContext as App
        repository = application.repository
    }

    override fun onDataSetChanged() {
        Log.d(TAG, "onDataSetChanged")
        runBlocking {
            val list = repository.fetchTitleList()
            titleList.clear()
            titleList.addAll(list)
        }
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
}