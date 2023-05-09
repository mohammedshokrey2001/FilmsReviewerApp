package com.example.myapplication_caching.domain.repo

import android.util.Log
import com.example.myapplication_caching.data.api.AppApi
import com.example.myapplication_caching.data.api.NetworkUtility
import com.example.myapplication_caching.domain.mappers.asDomainModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class AppRepository {


    private suspend fun getFilmTMDB(): List<FilmsDomainModel> {

        return withContext(Dispatchers.IO) {
           //    delay(10000)

            val response = AppApi.filmsApiTMDB.getFilm()
            Log.i("Fetch_API", "getFilmTMDB: $response")
            val data = NetworkUtility.generateListFromTdmbApi(
                JSONObject(response)
            )
            Log.i("Fetch_API", "getFilmTMDB: $data")
            data.asDomainModel()

        }

    }


    private suspend fun getFilmMiniMovie(): List<FilmsDomainModel> {
        return withContext(Dispatchers.IO) {
            val response = AppApi.filmsApiMiniMovie.getFilm()

            Log.i("Fetch_API", "getFilmMiniMovie: $response")
            val data = NetworkUtility.generateListFromMiniFilmsApi(
                JSONObject(response)
            )

            Log.i("Fetch_API", "getFilmMiniMovie: $data")
            data.asDomainModel()
        }
    }

    suspend fun cachingDataMultiApis(): Result<Pair<List<FilmsDomainModel>, List<FilmsDomainModel>>> {

        return try {
            withContext(Dispatchers.IO) {
                val api1Data = async { getFilmTMDB() }
                val api2Data = async { getFilmMiniMovie() }

                val tdmpResult = try {
                    Result.success(api1Data.await())

                } catch (e: Exception) {
                    error(e.message.toString())
                }

                val miniResult = try {
                    Result.success(api2Data.await())
                } catch (e: Exception) {
                    error(e.message.toString())
                }

                when {
                    tdmpResult.isFailure && miniResult.isFailure ->
                        Result.failure(Exception("Both API calls failed"))


                    miniResult.isFailure ->
                        Result.success(Pair(emptyList(), tdmpResult.getOrNull() ?: emptyList()))


                    tdmpResult.isFailure ->
                        Result.success(Pair(miniResult.getOrNull() ?: emptyList(), emptyList()))


                    else -> {
                        Result.success(
                            Pair(
                                tdmpResult.getOrNull() ?: emptyList(),
                                miniResult.getOrNull() ?: emptyList()
                            )
                        )
                    }
                }
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}