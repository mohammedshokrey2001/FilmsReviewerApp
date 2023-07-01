package com.example.myapplication_caching.ui.screens.players.youtube

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.ActivityYoutubeBinding
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory
import com.example.myapplication_caching.utilites.Utilities
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class YoutubeActivity :  YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {

    private lateinit var binding:ActivityYoutubeBinding
    private lateinit var filmId :String

   private val TAG = "YoutubeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i(TAG, "onCreate: youtube")
        binding = DataBindingUtil.setContentView(this,R.layout.activity_youtube)

            filmId = intent.getStringExtra(PlayerFactory.URL).toString()

        binding.youtubePlayerView.initialize(Utilities.YOUTUBE_KEY, this)



    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.loadVideo(filmId)
        p1?.play()
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }


}



