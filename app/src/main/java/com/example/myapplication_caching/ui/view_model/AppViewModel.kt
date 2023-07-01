package com.example.myapplication_caching.ui.view_model

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication_caching.data.api.util.NetworkUtility
import com.example.myapplication_caching.data.db.getDataBase
import com.example.myapplication_caching.domain.repo.AppRepository
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import com.example.myapplication_caching.domain.use_case.GetDomainDataFilmsUseCase
import com.example.myapplication_caching.domain.use_case.GetDomainDataTrailUseCase
import com.example.myapplication_caching.ui.screens.players.base.factory.PlayerFactory
import com.example.myapplication_caching.utilites.Utilities
import kotlinx.coroutines.launch

class AppViewModel(private val application: Application) : AndroidViewModel(application) {

    private val TAG = "AppViewModel"
    private val useCaseFilms = GetDomainDataFilmsUseCase(getDataBase(application))
    private val useCaseTrail = GetDomainDataTrailUseCase(getDataBase(application))

    val notifyDownloadComplete = MutableLiveData<Boolean>(false)

    private var _task = true
    val  task get() = _task


    private var _data = ArrayList<FilmsDomainModel>()
    val data get() = _data

    fun notifyStart() {
        Log.i(TAG, "notify: done")
    }

    suspend fun getData(): Boolean {
        val data = useCaseFilms.getFilms()
        notifyDownloadComplete.postValue(true)

        _data = data as ArrayList<FilmsDomainModel>
        _task = data.isNotEmpty()

        return true
    }

    suspend fun getFilmTrail(id: Int, context: Context) {
        val filmTrail = useCaseTrail.getFilmTrail(id)

        if (filmTrail.key == NetworkUtility.ERROR)
            Toast.makeText(
                application.baseContext,
                "The resource you requested could not be found.",
                Toast.LENGTH_LONG
            ).show()
        else {
            Log.i(TAG, "getFilmTrail: $filmTrail")
            // if you want to play youtube change  PlayerFactory.THIRD_PARTY with PlayerFactory.YOUTUBE
            val dest = PlayerFactory.createPlayer(
                context,
                PlayerFactory.THIRD_PARTY,
                Utilities.DEMO_URL_EXO
            )
            Log.i(TAG, "getFilmTrail: $dest")

            context.startActivity(dest)
        }
    }


}




