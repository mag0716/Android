package com.github.mag0716.exoplayerdownloadersample

import android.app.Notification
import android.util.Log
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.scheduler.PlatformScheduler
import com.google.android.exoplayer2.scheduler.Scheduler
import com.google.android.exoplayer2.ui.DownloadNotificationUtil
import com.google.android.exoplayer2.util.NotificationUtil
import com.google.android.exoplayer2.util.Util

class DownloadServiceWithLogging : DownloadService(FOREGROUND_NOTIFICATION_ID,
        DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
        CHANNEL_ID,
        R.string.exo_download_notification_channel_name) {

    companion object {
        const val CHANNEL_ID = "ExoPlayerDownloaderSample"
        private const val JOB_ID = 1
        private const val FOREGROUND_NOTIFICATION_ID = 1
    }

    override fun getScheduler(): Scheduler? {
        Log.d(App.TAG, "getScheduler")
        return PlatformScheduler(this, JOB_ID)
    }

    override fun getDownloadManager(): DownloadManager {
        Log.d(App.TAG, "getDownloadManager")
        return (application as App).downloadManager
    }

    override fun onTaskStateChanged(taskState: DownloadManager.TaskState?) {
        Log.d(App.TAG, "onTaskStateChanged : $taskState")
        super.onTaskStateChanged(taskState)

        if (taskState == null) {
            return
        }

        val action = taskState.action
        val state = taskState.state

        if (action.isRemoveAction) {
            return
        } else {
            var notification: Notification? = null
            if (state == DownloadManager.TaskState.STATE_COMPLETED) {
                notification = DownloadNotificationUtil.buildDownloadCompletedNotification(
                        this,
                        R.drawable.exo_notification_small_icon,
                        CHANNEL_ID,
                        null,
                        Util.fromUtf8Bytes(action.data)
                )
            } else if (state == DownloadManager.TaskState.STATE_FAILED) {
                notification = DownloadNotificationUtil.buildDownloadFailedNotification(
                        this,
                        R.drawable.exo_notification_small_icon,
                        CHANNEL_ID,
                        null,
                        Util.fromUtf8Bytes(action.data)
                )
            }

            notification?.let {
                val notificationId = FOREGROUND_NOTIFICATION_ID + 1 + taskState.taskId
                NotificationUtil.setNotification(this, notificationId, it)
            }
        }
    }
}
