package com.example.myapplication_caching.utilites.player

import android.content.Context
import android.view.ViewGroup

interface OwnVideoPlayer {

    fun initialize( url:String)
    fun stop()

    fun play()


}
