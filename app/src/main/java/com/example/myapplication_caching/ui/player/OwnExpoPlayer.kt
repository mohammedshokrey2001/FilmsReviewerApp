package com.example.myapplication_caching.ui.player

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class OwnExpoPlayer:OwnVideoPlayer{
    private lateinit var player :ExoPlayer

    override fun initialize(container: ViewGroup, url: String, context: Context) {
        player = ExoPlayer.Builder(context).build()

        val playerView = PlayerView(context)
        playerView.player = player

        container.addView(playerView)
        val mediaItem =
            MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()


    }

    override fun stop() {
       player.stop()
    }

    override fun play() {
        player.play()
    }


}