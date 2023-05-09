package com.example.myapplication_caching.data.api

import retrofit2.http.GET




interface FilmsApiTDMB {

   @GET("movie/popular?api_key=d7132d47f83deb0b3369582fd105134e")
  suspend fun getFilm():String

}