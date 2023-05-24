package com.example.myapplication_caching.ui.screens.players.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication_caching.R
import com.example.myapplication_caching.ui.screens.players.youtuve.YoutubeActivity
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory

class BasePlayerActivity : AppCompatActivity() {


    private val TAG = "BasePlayerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_player2)
        Log.i(TAG, "onCreate: youtube")
        val playerType = intent.getStringExtra(PlayerFactory.PLAYER_TYPE)
        val url = intent.getStringExtra(PlayerFactory.URL)
        val extra = Bundle()
        extra.putString(PlayerFactory.URL,url)


        if (playerType== PlayerFactory.THIRD_PARTY){
            //  val dest = Intent(this,YoutubeActivity::class.java)
            //   startActivity(dest,extra)

        }
        else if(playerType == PlayerFactory.YOUTUBE){
            val dest = Intent(this, YoutubeActivity::class.java)
            startActivity(dest,extra)
        }

    }
}