package com.github.mag0716.exoplayerdownloadersample

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.offline.*
import com.google.android.exoplayer2.ui.DefaultTrackNameProvider
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.Util
import java.io.File
import java.io.IOException

class DownloadTracker(
        context: Context,
        private val dataSourceFactory: DataSource.Factory,
        actionFile: File)
    : DownloadManager.Listener, DownloadHelper.Callback {

    private val appContext: Context = context.applicationContext
    private val trackNameProvider = DefaultTrackNameProvider(context.resources)
    private val trackedDownloadStateMap = mutableMapOf<Uri, DownloadAction>()
    private val actionFile = ActionFile(actionFile)
    private val actionFileWriteHandler: Handler

    init {
        val actionFileWriteThread = HandlerThread("DownloadThread")
        actionFileWriteThread.start()
        actionFileWriteHandler = Handler(actionFileWriteThread.looper)
        loadTrackedActions()
    }

    // region DownloadManager.Listener

    override fun onIdle(downloadManager: DownloadManager?) {
        Log.d(App.TAG, "onIdle")
    }

    override fun onInitialized(downloadManager: DownloadManager?) {
        Log.d(App.TAG, "onInitialized")
    }

    override fun onTaskStateChanged(downloadManager: DownloadManager?, taskState: DownloadManager.TaskState?) {
        Log.d(App.TAG, "onTaskStateChanged : $taskState")
        val action = taskState?.action
        val state = taskState?.state

        if (action != null && state != null) {
            val uri = action.uri
            if (action.isRemoveAction && state == DownloadManager.TaskState.STATE_COMPLETED ||
                    action.isRemoveAction.not() && state == DownloadManager.TaskState.STATE_FAILED) {
                if (trackedDownloadStateMap.remove(uri) != null) {
                    saveTrackedActions()
                }
            }
        }
    }

    // endregion

    // region DownloadHelper.Callback

    override fun onPrepared(helper: DownloadHelper?) {
        Log.d(App.TAG, "onPrepared")
        val trackKeyList = mutableListOf<TrackKey>()

        if (helper != null) {
            // TODO: 具体的に何をやっているのかが不明
            for (i in 0..helper.periodCount) {
                val trackGroups = helper.getTrackGroups(i)
                for (j in 0..trackGroups.length) {
                    val trackGroup = trackGroups[j]
                    for (k in 0..trackGroup.length) {
                        trackKeyList.add(TrackKey(i, j, k))
                    }
                }
            }

            val downloadAction = helper.getDownloadAction(Util.getUtf8Bytes("download"), trackKeyList)
            startServiceWithAction(downloadAction)
        }
    }

    override fun onPrepareError(helper: DownloadHelper?, e: IOException?) {
        if (e != null) {
            Log.w(App.TAG, "onPrepareError", e)
        } else {
            Log.d(App.TAG, "onPrepareError")
        }
    }

    // endregion

    fun download(uri: Uri) {
        if (isDownloaded(uri)) {
            return
        }
        getDownloadHelper(uri).prepare(this)
    }

    fun remove(uri: Uri) {
        val removeAction = getDownloadHelper(uri)
                .getRemoveAction(Util.getUtf8Bytes("remove"))
        startServiceWithAction(removeAction)
    }

    fun isDownloaded(uri: Uri) = trackedDownloadStateMap.containsKey(uri)

    private fun download(uri: Uri, trackKeyList: List<TrackKey>) {
        val downloadAction = getDownloadHelper(uri).getDownloadAction(Util.getUtf8Bytes("download"), trackKeyList)
        downloadAction?.let {
            trackedDownloadStateMap[uri] = downloadAction
            startServiceWithAction(it)
        }
    }

    private fun startServiceWithAction(action: DownloadAction) {
        DownloadService.startWithAction(appContext,
                DownloadServiceWithLogging::class.java,
                action,
                true)
    }

    private fun getDownloadHelper(uri: Uri): DownloadHelper {
        val format = Util.inferContentType(uri)
        return when (format) {
            C.TYPE_OTHER -> ProgressiveDownloadHelper(uri)
            else -> throw IllegalStateException("support only mp4 : $uri")
        }
    }

    private fun loadTrackedActions() {
        val allActions = actionFile.load(*DownloadAction.getDefaultDeserializers())
        for (action in allActions) {
            trackedDownloadStateMap[action.uri] = action
        }
    }

    private fun saveTrackedActions() {
        val actions = trackedDownloadStateMap.values.toTypedArray()
        actionFileWriteHandler.post {
            actionFile.store(*actions)
        }
    }
}