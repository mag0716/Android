package com.github.mag0716.exoplayerdownloadersample

import android.app.Application
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
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
        private val BANDWIDTH_METER = DefaultBandwidthMeter()
    }

    lateinit var dataSourceFactory: DataSource.Factory

    lateinit var downloadManager: DownloadManager
    lateinit var downloadDirectory: File
    lateinit var downloadCache: Cache
    lateinit var downloadTracker: MP4DownloadTracker

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val userAgent = Util.getUserAgent(
                this,
                "ExoPlayerSample"
        )

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
                BANDWIDTH_METER,
                DefaultHttpDataSourceFactory(
                        userAgent,
                        BANDWIDTH_METER)
        )
        dataSourceFactory = CacheDataSourceFactory(downloadCache, mediaDataSourceFactory)

        // DownloadManager
        val downloaderConstructorHelper = DownloaderConstructorHelper(
                downloadCache,
                DefaultHttpDataSourceFactory(userAgent))
        downloadManager = DownloadManager(
                downloaderConstructorHelper,
                1,
                DownloadManager.DEFAULT_MIN_RETRY_COUNT,
                File(downloadDirectory, "downloadActions")
        )

        // MP4DownloadTracker
        downloadTracker = MP4DownloadTracker(this,
                dataSourceFactory,
                File(downloadDirectory, "trackedActions"))
        downloadManager.addListener(downloadTracker)
    }
}