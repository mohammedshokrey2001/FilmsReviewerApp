package com.example.myapplication_caching.ui.adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_caching.databinding.ListItemFilmsBinding
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import com.example.myapplication_caching.ui.view_model.AppViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

class FilmAdapter(private val filmsList: List<FilmsDomainModel>
,private val viewModel :AppViewModel,private val context: Context) :RecyclerView.Adapter<FilmViewHolder>() {

    private lateinit var binding:ListItemFilmsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        binding = ListItemFilmsBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return FilmViewHolder(binding=binding)
    }

    override fun getItemCount() = filmsList.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(film = filmsList[position])
        holder.itemView.rootView.setOnClickListener {

            viewModel.viewModelScope.launch {
                viewModel.getFilmTrail(filmsList[position].id, context =context )
            }
        }
    }
}