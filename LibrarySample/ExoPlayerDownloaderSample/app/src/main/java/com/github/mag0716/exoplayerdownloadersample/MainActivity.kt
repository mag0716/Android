package com.github.mag0716.exoplayerdownloadersample

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.PlaybackPreparer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView


class MainActivity : AppCompatActivity(), PlaybackPreparer {

    companion object {
        private const val TEST_URL = "https://html5demos.com/assets/dizzy.mp4"
    }

    private lateinit var mainHandler: Handler
    private lateinit var playerView: PlayerView
    private lateinit var textView: TextView
    private lateinit var downloadButton: Button
    private lateinit var removeButton: Button

    private var player: SimpleExoPlayer? = null
    private var trackSelector: DefaultTrackSelector? = null

    private lateinit var downloadTracker: DownloadTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadTracker = (applicationContext as App).downloadTracker

        mainHandler = Handler()
        playerView = findViewById(R.id.player_view)
        textView = findViewById(R.id.textView)
        downloadButton = findViewById(R.id.download)
        downloadButton.setOnClickListener {
            download()
        }
        removeButton = findViewById(R.id.remove)
        removeButton.setOnClickListener {
            remove()
        }
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()

        // TODO: ダウンロード、削除完了後の更新は未対応
        textView.text = if (downloadTracker.isDownloaded(Uri.parse(TEST_URL))) "Downloaded" else "No Download"
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
    }

    private fun releasePlayer() {
        val player = player
        if (player != null) {
            player.release()
            this.player = null
        }
    }

    private fun download() {
        downloadTracker.download(Uri.parse(TEST_URL))
    }

    private fun remove() {
        downloadTracker.remove(Uri.parse(TEST_URL))
    }
}
