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

/**
 * Notification でダウンロード状態を通知する DownloadService
 *
 * サポート
 *     ダウンロード中
 *     ダウンロード成功
 *     ダウンロード失敗
 *
 * 未サポート
 *     キャッシュファイルの削除
 */
class NotifiableDownloadService : DownloadService(FOREGROUND_NOTIFICATION_ID,
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

    override fun getForegroundNotification(taskStates: Array<out DownloadManager.TaskState>?): Notification {
        return DownloadNotificationUtil.buildProgressNotification(
                this,
                R.drawable.exo_controls_play,
                CHANNEL_ID,
                null,
                null,
                taskStates
        )
    }

    override fun onTaskStateChanged(taskState: DownloadManager.TaskState?) {
        Log.d(App.TAG, "NotifiableDownloadService#onTaskStateChanged : ${taskState?.action} : ${taskState?.state}")
        super.onTaskStateChanged(taskState)

        if (taskState == null) {
            return
        }

        val action = taskState.action
        val state = taskState.state

        if (action.isRemoveAction) {
            // not support
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
