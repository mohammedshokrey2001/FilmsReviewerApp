package com.example.myapplication_caching.data.api

import com.example.myapplication_caching.utilites.Utilities
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FilmsApiTDMB {

    @GET("movie/popular?api_key=d7132d47f83deb0b3369582fd105134e")
    suspend fun getFilm(): String


    @GET("movie/{id}/videos")
    suspend fun getFilmTrail(
        @Path("id") id: Int, @Query("api_key") apiKey: String = Utilities.API_KEY,
        @Query("language") language: String = Utilities.LANGUAGE
    ): String


}