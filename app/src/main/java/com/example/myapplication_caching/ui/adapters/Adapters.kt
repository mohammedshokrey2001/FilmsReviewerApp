package com.example.myapplication_caching.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_caching.databinding.ListItemFilmsBinding
import com.example.myapplication_caching.domain.model.FilmsDomainModel

class FilmViewHolder(
    private val binding:ListItemFilmsBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(film:FilmsDomainModel){
            binding.film=film
        }

}
