package com.example.myapplication_caching.ui.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication_caching.domain.repo.AppRepository
import com.example.myapplication_caching.domain.model.FilmsDomainModel

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository()

    val notifyDownloadComplete = MutableLiveData<Boolean>(false)

    var task = true
    private var _data = ArrayList<FilmsDomainModel>()

    val data get() = _data

    init {

    }

    fun notifyStart() {
        Log.i("TAG", "notify: done")
    }


     suspend fun getDataFromTwoApis() {

      val result: Result<Pair<List<FilmsDomainModel>, List<FilmsDomainModel>>> =  repository.cachingDataMultiApisAsync()

        if (result.isSuccess){
            val (data1, data2) = result.getOrNull() ?: Pair(emptyList(), emptyList())
            _data.addAll(data1)
            _data.addAll(data2)
            notifyDownloadComplete.postValue(true)
        }
        else{
            notifyDownloadComplete.postValue(true)
            task = false
        }
    }

}




