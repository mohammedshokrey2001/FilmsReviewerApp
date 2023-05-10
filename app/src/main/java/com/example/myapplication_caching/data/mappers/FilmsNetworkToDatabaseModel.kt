package com.example.myapplication_caching.data.mappers

import com.example.myapplication_caching.data.db.FilmsDatabase
import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel

fun List<FilmsNetworkModel>.asDatabaseModel():List<FilmDatabaseModel>{
    return map {
        FilmDatabaseModel(
            movieName = it.movieName,
            popularity = it.popularity,
            id = it.id
        )
    }

}