package com.example.myapplication_caching.domain.repo

import com.example.myapplication_caching.data.api.RemoteDataSource
import com.example.myapplication_caching.data.db.FilmsDatabase
import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.data.mappers.asDatabaseModel
import com.example.myapplication_caching.data.models.FilmTrailNetworkModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception


class AppRepository(private val database: FilmsDatabase) {

    private val remoteDataSource = RemoteDataSource()

    private suspend fun getFilmTMDB(): List<FilmsNetworkModel> {
        return remoteDataSource.getDataFromApiTdmb()

    }
    private suspend fun getFilmMiniMovie(): List<FilmsNetworkModel> {
        return remoteDataSource.getDataFromApiMiniFilms()
    }

    suspend fun getDataMultiApis(): Result<Pair<List<FilmsNetworkModel>, List<FilmsNetworkModel>>> {

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
                    tdmpResult.isFailure && miniResult.isFailure -> Result.failure(Exception("Both API calls failed"))

                    miniResult.isFailure -> Result.success(
                        Pair(
                            emptyList(),
                            tdmpResult.getOrNull() ?: emptyList()
                        )
                    )

                    tdmpResult.isFailure -> Result.success(
                        Pair(
                            miniResult.getOrNull() ?: emptyList(), emptyList()
                        )
                    )

                    else -> {
                        Result.success(
                            //test

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


    suspend fun cachingData(): Boolean {
        val filmList = ArrayList<FilmDatabaseModel>()

        val response = getDataMultiApis()
        if (response.isSuccess) {
            if (response != null) {

                val (data1, data2) = response.getOrNull() ?: Pair(emptyList(), emptyList())
                filmList.addAll(data1.asDatabaseModel())
                // filmList.addAll(data2.asDatabaseModel())

                database.filmsDao.saveToDatabase(*filmList.toTypedArray())
                return true
            }
        }
        return false

    }

    suspend fun getFilmsFromDatabase(): List<FilmDatabaseModel> {
        return database.filmsDao.getFilms()
    }

    suspend fun getFilmTrail(id: Int): FilmTrailNetworkModel {
        return remoteDataSource.getFilmTrail(id)

    }


}