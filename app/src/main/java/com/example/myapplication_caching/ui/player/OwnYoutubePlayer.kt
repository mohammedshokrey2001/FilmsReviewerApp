package com.example.myapplication_caching.ui.player

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication_caching.utilites.Utilities
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView

class OwnYoutubePlayer :OwnVideoPlayer {

    private lateinit var url :String
    private lateinit var context:Context
    lateinit var youTubePlayerView : YouTubePlayerView
    override fun initialize(container: ViewGroup, url: String, context: Context) {

        youTubePlayerView = YouTubePlayerView(context)

        container.addView(youTubePlayerView)

        this.url = url
        this.context = context

    }

    override fun stop() {


    }

    override fun play() {

        youTubePlayerView.initialize(Utilities.YOUTUBE_KEY,object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                //   "Vxlma4Vnuw"
                p1?.loadVideo(url)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(context,"error loading your video",Toast.LENGTH_LONG).show()
            }

        })

    }
}