package com.example.myapplication_caching.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.FragmentFilmsBinding
import com.example.myapplication_caching.ui.view_model.AppViewModel
import com.example.myapplication_caching.ui.adapters.FilmAdapter

class FilmsFragment : Fragment() {

   private lateinit var binding:FragmentFilmsBinding
     private val viewModel: AppViewModel by activityViewModels (  )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil .inflate(inflater,R.layout.fragment_films, container, false)

        val adapter =  FilmAdapter(viewModel.data,viewModel)
        val rv = binding.rvFilms
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this.requireContext())
        rv.adapter = adapter
        return binding.root
    }



}