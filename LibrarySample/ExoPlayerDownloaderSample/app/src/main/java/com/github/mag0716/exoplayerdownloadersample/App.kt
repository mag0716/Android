package com.github.mag0716.exoplayerdownloadersample

import android.app.Application
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

class App : Application() {

    companion object {
        const val USER_AGENT = "ExoPlayerDownloaderSample"
    }

    private lateinit var downloadManager: DownloadManager

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        var downloadDirectory = getExternalFilesDir(null)
        if (downloadDirectory == null) {
            downloadDirectory = filesDir
        }
        val downloadCache = SimpleCache(
                File(downloadDirectory, "downloads"),
                NoOpCacheEvictor())
        val downloaderConstructorHelper = DownloaderConstructorHelper(
                downloadCache,
                DefaultHttpDataSourceFactory(USER_AGENT))
        downloadManager = DownloadManager(
                downloaderConstructorHelper,
                1,
                DownloadManager.DEFAULT_MIN_RETRY_COUNT,
                File(downloadDirectory, "downloadActions")
        )
    }
}