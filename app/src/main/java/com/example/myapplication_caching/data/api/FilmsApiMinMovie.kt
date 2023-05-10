package com.example.myapplication_caching.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface FilmsApiMinMovie {


    @Headers("X-Rapidapi-Key:3e4813ed65msh7e172e80ed4c732p13f833jsnd68a26702770")
    @GET("movie/order/byPopularity/")
    suspend fun getFilm():String

 }