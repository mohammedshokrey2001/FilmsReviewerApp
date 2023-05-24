package com.example.myapplication_caching.ui.screens.players.third_party.exo

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.FragmentExoBinding
import com.example.myapplication_caching.utilites.player.OwnVideoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


class ExoFragment(private val url:String) : Fragment(),OwnVideoPlayer {
    private lateinit var  player: SimpleExoPlayer
    private lateinit  var playerView: PlayerView
    private lateinit var binding:FragmentExoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_exo, container, false)


        playerView = binding.playerView
        initialize(url)
        play()

        return binding.root
    }


    override fun initialize(url: String) {
        player = SimpleExoPlayer.Builder(this.requireActivity()).build()
        playerView.player = player

        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(url))
        player.setMediaItem(mediaItem)
        player.prepare()

    }

    override fun stop() {
        player.stop()

    }

    override fun play() {
        player.play()
    }


companion object{
    const val URL ="url"


}
}