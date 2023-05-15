package com.example.myapplication_caching.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.FragmentTrailBinding
import com.example.myapplication_caching.ui.player.OwnExpoPlayer
import com.example.myapplication_caching.ui.player.OwnVideoPlayer
import com.example.myapplication_caching.ui.player.OwnYoutubePlayer
import com.example.myapplication_caching.utilites.Utilities
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment


class TrailFragment : Fragment() {


    lateinit var binding: FragmentTrailBinding

    lateinit var player: OwnVideoPlayer

    // lateinit var youtubePlayerInit :YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trail, container, false)


       val exoPlayer = OwnExpoPlayer()
        exoPlayer.initialize(
            binding.playerContainer,
            "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4",
            this.requireContext()
        )

//
//        val youtubePlayer = OwnYoutubePlayer()
//        youtubePlayer.initialize(binding.playerContainer,"Vxlma4Vnuw",this.requireContext())

        player = exoPlayer
        player.play()

        return binding.root


    }

    override fun onDestroy() {
        super.onDestroy()


    }

}
