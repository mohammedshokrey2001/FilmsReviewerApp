package com.example.myapplication_caching.ui.player

import android.content.Context
import android.view.ViewGroup

interface OwnVideoPlayer {

    fun initialize(container:ViewGroup, url:String, context:Context)
    fun stop()

    fun play()
}
