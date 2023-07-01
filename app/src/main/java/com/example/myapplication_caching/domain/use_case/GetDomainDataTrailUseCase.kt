package com.example.myapplication_caching.domain.use_case

import com.example.myapplication_caching.data.db.FilmsDatabase
import com.example.myapplication_caching.domain.mappers.asDomainModel
import com.example.myapplication_caching.domain.model.FilmTrailDomainModel
import com.example.myapplication_caching.domain.repo.AppRepository

class GetDomainDataTrailUseCase (database: FilmsDatabase){

    private val appRepository = AppRepository(database)

    suspend fun getFilmTrail(id:Int): FilmTrailDomainModel {
        return appRepository.getFilmTrail(id).asDomainModel()
    }
}