package com.example.myapplication_caching.ui.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication_caching.data.db.getDataBase
import com.example.myapplication_caching.domain.repo.AppRepository
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import com.example.myapplication_caching.domain.use_case.GetDomainDataUseCase
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val useCaseFilms  = GetDomainDataUseCase(getDataBase(application))

    private val repository = AppRepository(getDataBase(application))
    val notifyDownloadComplete = MutableLiveData<Boolean>(false)

    var task = true
    private var _data = ArrayList<FilmsDomainModel>()
    val data get() = _data

    init {
        viewModelScope.launch {
         val  cacheResponse =   repository.cachingData()
        }
    }

    fun notifyStart() {
        Log.i("TAG", "notify: done")
    }


    suspend fun getData():Boolean{
        val data =   useCaseFilms.getFilms()
        notifyDownloadComplete.postValue(true)

        _data = data as ArrayList<FilmsDomainModel>
        notifyDownloadComplete.postValue(true)
         task = data.isNotEmpty()

        return true
    }

  suspend  fun getFilmTrail(id:Int){

        useCaseFilms.getFilmTrail(id)
    }
}




