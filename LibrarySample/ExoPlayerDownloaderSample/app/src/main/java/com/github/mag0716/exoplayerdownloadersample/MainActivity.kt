package com.github.mag0716.exoplayerdownloadersample

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.net.CookieManager
import java.net.CookiePolicy


class MainActivity : AppCompatActivity(), PlaybackPreparer {

    companion object {
        val BANDWIDTH_METER = DefaultBandwidthMeter()
        val DEFAULT_COOKIE_MANAGER = CookieManager().apply { setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER) }

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
        mediaDataSourceFactory = DefaultDataSourceFactory(this,
                BANDWIDTH_METER,
                DefaultHttpDataSourceFactory(
                        Util.getUserAgent(
                                this,
                                "ExoPlayerSample"
                        ),
                        BANDWIDTH_METER)
        )
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initializePlayer() {
        val adaptiveTrackSelectionFactory = AdaptiveTrackSelection.Factory(BANDWIDTH_METER)
        trackSelector = DefaultTrackSelector(adaptiveTrackSelectionFactory)
        val renderersFactory = DefaultRenderersFactory(this,
                null,
                DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF)

        val player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector)
        this.player = player
        player.addListener(PlayerEventListener())
        player.playWhenReady = true

        playerView.player = player
        playerView.setPlaybackPreparer(this)
        val mediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(Uri.parse(TEST_URL), mainHandler, null)
        player.prepare(mediaSource)
    }

    private fun releasePlayer() {
        val player = player
        if (player != null) {
            player.release()
            this.player = null
        }
    }

    private class PlayerEventListener : Player.DefaultEventListener() {
        override fun onPlayerError(error: ExoPlaybackException?) {
            super.onPlayerError(error)
        }
    }
}
