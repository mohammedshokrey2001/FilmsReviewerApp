package com.example.myapplication_caching.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_caching.databinding.ListItemFilmsBinding
import com.example.myapplication_caching.domain.model.FilmsDomainModel

class FilmAdapter(private val filmsList: List<FilmsDomainModel>
) :RecyclerView.Adapter<FilmViewHolder>() {

    private lateinit var binding:ListItemFilmsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        binding = ListItemFilmsBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return FilmViewHolder(binding=binding)
    }

    override fun getItemCount() = filmsList.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(film = filmsList[position])
    }
}