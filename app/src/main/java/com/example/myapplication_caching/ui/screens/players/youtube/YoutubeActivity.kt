package com.example.myapplication_caching.ui.screens.players.youtube

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication_caching.BuildConfig
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.ActivityYoutubeBinding
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory
import com.example.myapplication_caching.ui.screens.players.player_interface.OwnVideoPlayer
import com.example.myapplication_caching.utilites.Utilities
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class YoutubeActivity :  YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener , OwnVideoPlayer{

    private lateinit var binding:ActivityYoutubeBinding
    private lateinit var filmId :String

    private  var player:YouTubePlayer? = null
    private val youtubeKey = BuildConfig.YOUTUBE_KEY

   private val TAG = "YoutubeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i(TAG, "onCreate: youtube")
        binding = DataBindingUtil.setContentView(this,R.layout.activity_youtube)



        initialize(filmId)

    }
    override fun initialize(url: String) {
        filmId = intent.getStringExtra(PlayerFactory.URL).toString()
        binding.youtubePlayerView.initialize(youtubeKey, this)

    }


    override fun stop() {
        player?.pause()
        Toast.makeText(this, "cant play you video", Toast.LENGTH_SHORT).show()

    }

    override fun play() {
        player?.play()
        Log.i(TAG, "onInitializationSuccess: done")

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        player = p1
        p1?.loadVideo(filmId)
        play()
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.i(TAG, "onInitializationFailure:failed ${p1.toString()}")
    }


    override fun onStop() {
        super.onStop()
        stop()
    }
}



