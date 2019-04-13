package com.github.mag0716.exoplayerdownloadersample

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.offline.*
import com.google.android.exoplayer2.util.Util
import java.io.File
import java.io.IOException

/**
 * ダウンロードイベントをハンドリングしてダウンロード状態を管理する
 *
 * mp4 のダウンロードのみをサポート
 */
class MP4DownloadTracker(
        context: Context,
        actionFile: File)
    : DownloadManager.Listener, DownloadHelper.Callback {

    private val appContext: Context = context.applicationContext
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
        val action = taskState?.action
        val state = taskState?.state
        Log.d(App.TAG, "MP4DownloadTracker#onTaskStateChanged : $action(${action?.isRemoveAction} : $state")

        if (action != null && state != null) {
            val uri = action.uri
            val isRemoveCompleted = action.isRemoveAction && state == DownloadManager.TaskState.STATE_COMPLETED
            val isDownloadFailed = action.isRemoveAction.not() && state == DownloadManager.TaskState.STATE_FAILED
            if (isDownloadFailed || isRemoveCompleted) {
                trackedDownloadStateMap.remove(uri)
                saveTrackedActions()
            }
        }
    }

    // endregion

    // region DownloadHelper.Callback

    override fun onPrepared(helper: DownloadHelper?) {
        Log.d(App.TAG, "onPrepared")
        if (helper != null) {
            val downloadAction = helper.getDownloadAction(Util.getUtf8Bytes("download"), listOf())
            trackedDownloadStateMap[downloadAction.uri] = downloadAction
            saveTrackedActions()
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

    // region private

    private fun startServiceWithAction(action: DownloadAction) {
        DownloadService.startWithAction(appContext,
                NotifiableDownloadService::class.java,
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
        Log.d(App.TAG, "loadTrackedActions : $trackedDownloadStateMap")
    }

    private fun saveTrackedActions() {
        Log.d(App.TAG, "saveTrackedActions : $trackedDownloadStateMap")
        val actions = trackedDownloadStateMap.values.toTypedArray()
        actionFileWriteHandler.post {
            actionFile.store(*actions)
        }
    }

    // endregion
}