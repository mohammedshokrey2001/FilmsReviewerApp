package com.example.myapplication_caching.ui.screens.players.base.factory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication_caching.ui.screens.players.youtube.YoutubeActivity
import com.example.myapplication_caching.ui.screens.players.third_party.ThirdPartyPlayersActivity

object PlayerFactory {

    const val YOUTUBE = "youtube"
    const val THIRD_PARTY = "third"
    const val PLAYER_TYPE ="player_type"
    const val URL = "url"
    const val DATA = "data"


    lateinit var intent: Intent
    fun createPlayer(context: Context, playerType: String, url:String): Intent {

        val extra = Bundle()
        extra.putString(PLAYER_TYPE, playerType)
        extra.putString(URL,url)

       if (playerType== YOUTUBE){
           intent =  Intent(context, YoutubeActivity::class.java)
            intent.putExtra(DATA,extra)

        } else{
           intent =  Intent(context, ThirdPartyPlayersActivity::class.java)
            intent.putExtra(DATA,extra)

        }



        return intent


    }

}