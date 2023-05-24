package com.example.myapplication_caching.ui.screens.players.third_party


import android.R.attr.fragment
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.ActivityThirdPartyPlayersBinding
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory
import com.example.myapplication_caching.ui.screens.players.third_party.exo.ExoFragment


class ThirdPartyPlayersActivity : AppCompatActivity(){


    lateinit var
            binding: ActivityThirdPartyPlayersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_third_party_players)

        val bundle = intent.getBundleExtra(PlayerFactory.DATA)!!
        val url = bundle.getString(PlayerFactory.URL)
        Log.i("ThirdPartyPlayersActivity", "onCreate: $url")
        val exoFragment = ExoFragment(url!!)

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, exoFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }




}