package com.example.myapplication_caching.domain.mappers

import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel


fun List<FilmDatabaseModel>.asDomainModel():List<FilmsDomainModel>{

    return map {
        FilmsDomainModel(
            movieName = it.movieName,
            id = it.id,
            popularity = it.popularity
        )
    }

}