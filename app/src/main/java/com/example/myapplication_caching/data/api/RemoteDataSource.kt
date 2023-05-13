package com.example.myapplication_caching.data.api

import android.util.Log
import com.example.myapplication_caching.data.models.FilmTrailNetworkModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import com.example.myapplication_caching.domain.mappers.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject


class RemoteDataSource {

 suspend fun getDataFromApiTdmb() :List<FilmsNetworkModel>{
     return withContext(Dispatchers.IO) {
         delay(10000)
         val response = AppApi.filmsApiTMDB.getFilm()

         Log.i("Fetch_API", "getFilmTMDB: $response")
         val data = NetworkUtility.generateListFromTdmbApi(
             JSONObject(response)
         )
         Log.i("Fetch_API", "getFilmTMDB: $data")
         data

     }
 }


    suspend fun getFilmTrail(id:Int) :FilmTrailNetworkModel{
        return withContext(Dispatchers.IO){
            val response = AppApi.filmsApiTMDB.getFilmTrail(id)
           val dataAsNetworkModel = NetworkUtility.generateFilmsTrailFromApi(JSONObject(response))
           // Log.i("Fetch_API", "getFilmTMDBTrail: $dataAsNetworkModel")

            dataAsNetworkModel
        }
    }
    suspend fun getDataFromApiMiniFilms():List<FilmsNetworkModel>{

        return withContext(Dispatchers.IO) {
            val response = AppApi.filmsApiMiniMovie.getFilm()

            Log.i("Fetch_API", "getFilmMiniMovie: $response")

            val data = NetworkUtility.generateListFromMiniFilmsApi(
                JSONObject(response)
            )

            Log.i("Fetch_API", "getFilmMiniMovie: $data")
            data
        }
    }

}