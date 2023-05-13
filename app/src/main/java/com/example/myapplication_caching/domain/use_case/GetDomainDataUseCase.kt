package com.example.myapplication_caching.domain.use_case

import com.example.myapplication_caching.data.db.FilmsDatabase
import com.example.myapplication_caching.domain.mappers.asDomainModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel
import com.example.myapplication_caching.domain.repo.AppRepository

class GetDomainDataUseCase(database: FilmsDatabase) {

    private val appRepository = AppRepository(database)

    suspend fun getFilms():List<FilmsDomainModel> {
         val filmsList = ArrayList<FilmsDomainModel>()
        val result  = appRepository.getFilmsFromDatabase()
        filmsList.addAll(result.asDomainModel())

        return filmsList
    }

    suspend fun getFilmTrail(id:Int){
        appRepository.getFilmTrail(id)
    }

    }

