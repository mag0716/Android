package com.github.mag0716.exoplayerdownloadersample

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.PlaybackPreparer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter


class MainActivity : AppCompatActivity(), PlaybackPreparer {

    companion object {
        val BANDWIDTH_METER = DefaultBandwidthMeter()
        val TEST_URL = "https://html5demos.com/assets/dizzy.mp4"
    }

    private lateinit var mainHandler: Handler
    private lateinit var playerView: PlayerView

    private var player: SimpleExoPlayer? = null
    private var trackSelector: DefaultTrackSelector? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainHandler = Handler()
        playerView = findViewById(R.id.player_view)
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun preparePlayback() {
    }

    private fun initializePlayer() {
        val adaptiveTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        trackSelector = DefaultTrackSelector(adaptiveTrackSelectionFactory)
        val renderersFactory = DefaultRenderersFactory(this)

        val player = ExoPlayerFactory.newSimpleInstance(this, renderersFactory, trackSelector)
        this.player = player
        player.playWhenReady = true

        playerView.player = player
        playerView.setPlaybackPreparer(this)
        val uri = Uri.parse(TEST_URL)
        val mediaSource = ExtractorMediaSource.Factory(((applicationContext as App).dataSourceFactory)).createMediaSource(uri)
        player.prepare(mediaSource)

//        // main Thread だと NetworkOnMainThreadException になる
//        Thread(Runnable {
//            val downloader = ProgressiveDownloader(uri,
//                    TEST_URL,
//                    DownloaderConstructorHelper(cache, mediaDataSourceFactory))
//            downloader.download()
//        }).start()
    }

    private fun releasePlayer() {
        val player = player
        if (player != null) {
            player.release()
            this.player = null
        }
    }
}
