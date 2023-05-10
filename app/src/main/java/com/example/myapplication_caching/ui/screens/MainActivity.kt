package com.example.myapplication_caching.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication_caching.R
import com.example.myapplication_caching.data.db.getDataBase
import com.example.myapplication_caching.databinding.ActivityMainBinding
import com.example.myapplication_caching.domain.back_ground.RepositoryWorker
import com.example.myapplication_caching.domain.repo.AppRepository
import com.example.myapplication_caching.ui.view_model.AppViewModel
import com.example.myapplication_caching.ui.view_model.AppViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this, AppViewModelFactory(this.application))[AppViewModel::class.java]
    }

    lateinit var  repository :AppRepository

    private lateinit var binding :ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        repository  =  AppRepository(getDataBase(this))
       val repositoryWork = RepositoryWorker(this,repository)

        repositoryWork.start()
    }


}