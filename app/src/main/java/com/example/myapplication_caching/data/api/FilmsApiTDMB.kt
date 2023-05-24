package com.example.myapplication_caching.data.api

import com.example.myapplication_caching.BuildConfig
import com.example.myapplication_caching.utilites.Utilities
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FilmsApiTDMB {



    @GET("movie/popular")
    suspend fun getFilm(@Query("api_key")apiKey: String = BuildConfig.Api_Key_TMDB ): String


    @GET("movie/{id}/videos")
    suspend fun getFilmTrail(
        @Path("id") id: Int, @Query("api_key") apiKey: String = BuildConfig.Api_Key_TMDB,
        @Query("language") language: String = Utilities.LANGUAGE,

    ): String


}