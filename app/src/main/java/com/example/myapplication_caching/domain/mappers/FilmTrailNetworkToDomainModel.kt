package com.example.myapplication_caching.domain.mappers

import com.example.myapplication_caching.data.models.FilmTrailNetworkModel
import com.example.myapplication_caching.domain.model.FilmTrailDomainModel


fun FilmTrailNetworkModel.asDomainModel():FilmTrailDomainModel{
    return FilmTrailDomainModel(
        id = id
    )
}