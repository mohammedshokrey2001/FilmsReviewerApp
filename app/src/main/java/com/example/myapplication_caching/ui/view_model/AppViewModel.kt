package com.example.myapplication_caching.ui.view_model

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication_caching.data.api.NetworkUtility
import com.example.myapplication_caching.data.db.getDataBase
import com.example.myapplication_caching.domain.repo.AppRepository
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import com.example.myapplication_caching.domain.use_case.GetDomainDataUseCase
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory
import com.example.myapplication_caching.utilites.Utilities
import kotlinx.coroutines.launch

class AppViewModel(private val application: Application) : AndroidViewModel(application) {

    private val useCaseFilms = GetDomainDataUseCase(getDataBase(application))

    private val repository = AppRepository(getDataBase(application))
    val notifyDownloadComplete = MutableLiveData<Boolean>(false)

    var task = true
    private var _data = ArrayList<FilmsDomainModel>()
    val data get() = _data

    init {
        viewModelScope.launch {
            val cacheResponse = repository.cachingData()
        }
    }

    fun notifyStart() {
        Log.i("TAG", "notify: done")
    }


    suspend fun getData(): Boolean {
        val data = useCaseFilms.getFilms()
        notifyDownloadComplete.postValue(true)

        _data = data as ArrayList<FilmsDomainModel>
        notifyDownloadComplete.postValue(true)
        task = data.isNotEmpty()

        return true
    }

    suspend fun getFilmTrail(id: Int, context: Context) {
        val filmTrail = useCaseFilms.getFilmTrail(id)

        if (filmTrail.key == NetworkUtility.ERROR)
            Toast.makeText(
                application.baseContext,
                "The resource you requested could not be found.",
                Toast.LENGTH_LONG
            ).show()
        else {
            Log.i("ViewModel", "getFilmTrail: $filmTrail")

            val dest = PlayerFactory.createPlayer2(
                context,
                PlayerFactory.THIRD_PARTY,
                Utilities.DEMO_URL_EXO
            )
            Log.i("ViewModel", "getFilmTrail: $dest")

            context.startActivity(dest)
        }
    }


}




