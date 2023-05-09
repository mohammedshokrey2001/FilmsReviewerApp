package com.example.myapplication_caching.domain.mappers

import com.example.myapplication_caching.data.models.FilmsNetworkModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel


fun List<FilmsNetworkModel>.asDomainModel():List<FilmsDomainModel>{


    return map {
        FilmsDomainModel(
            movieName = it.movieName,
            id = it.id,
            popularity = it.popularity
        )
    }
}