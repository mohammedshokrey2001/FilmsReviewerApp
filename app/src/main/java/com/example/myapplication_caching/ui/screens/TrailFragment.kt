package com.example.myapplication_caching.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager

import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.FragmentTrailBinding
import com.example.myapplication_caching.utilites.Utilities
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer



class TrailFragment : Fragment()  {


    lateinit var binding: FragmentTrailBinding
   // lateinit var youtubePlayerInit :YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            context.supportActionBar?.hide()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trail, container, false)



//        youTubePlayerFragment.initialize(Utilities.YOUTUBE_KEY, object :YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                p1?.loadVideo("L0WGZSiOZsM")
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(requireActivity(),"failed",Toast.LENGTH_LONG).show()
//
//            }
//
//
//        })

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.show()
    }





    fun expo(){
        val player = ExoPlayer.Builder(this.requireContext()).build()
      //  binding.playerView.player = player
        // Build the media item.
        val mediaItem =
            MediaItem.fromUri("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4")
// Set the media item to be played.
        player.setMediaItem(mediaItem)
// Prepare the player.
        player.prepare()
// Start the playback.
        player.play()
    }

}
