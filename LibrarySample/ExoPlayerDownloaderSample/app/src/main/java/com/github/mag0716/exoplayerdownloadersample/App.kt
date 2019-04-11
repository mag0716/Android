package com.github.mag0716.exoplayerdownloadersample

import android.app.Application
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.util.Util
import java.io.File

class App : Application() {

    companion object {
        const val TAG = "ExoPlayerDownloader"
        const val USER_AGENT = "ExoPlayerDownloaderSample"
    }

    lateinit var dataSourceFactory: DataSource.Factory

    lateinit var downloadManager: DownloadManager
    lateinit var downloadDirectory: File
    lateinit var downloadCache: Cache
    lateinit var downloadTracker: DownloadTracker

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        // Cache
        var downloadDirectory = getExternalFilesDir(null)
        if (downloadDirectory == null) {
            downloadDirectory = filesDir
        }
        this.downloadDirectory = downloadDirectory!!
        downloadCache = SimpleCache(
                File(downloadDirectory, "downloads"),
                NoOpCacheEvictor())

        // DataSource.Factory
        val mediaDataSourceFactory = DefaultDataSourceFactory(this,
                MainActivity.BANDWIDTH_METER,
                DefaultHttpDataSourceFactory(
                        Util.getUserAgent(
                                this,
                                "ExoPlayerSample"
                        ),
                        MainActivity.BANDWIDTH_METER)
        )
        dataSourceFactory = CacheDataSourceFactory(downloadCache, mediaDataSourceFactory)

        // DownloadManager
        val downloaderConstructorHelper = DownloaderConstructorHelper(
                downloadCache,
                DefaultHttpDataSourceFactory(USER_AGENT))
        downloadManager = DownloadManager(
                downloaderConstructorHelper,
                1,
                DownloadManager.DEFAULT_MIN_RETRY_COUNT,
                File(downloadDirectory, "downloadActions")
        )

        // DownloadTracker
        downloadTracker = DownloadTracker(this,
                dataSourceFactory,
                File(downloadDirectory, "trackedActions"))
    }
}