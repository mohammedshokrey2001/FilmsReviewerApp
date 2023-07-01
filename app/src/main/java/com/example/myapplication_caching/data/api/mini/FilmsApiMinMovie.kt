package com.example.myapplication_caching.data.api.mini

import com.example.myapplication_caching.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface FilmsApiMinMovie {



    @Headers("X-Rapidapi-Key:${BuildConfig.Api_Key_MINI_MOVIE}")
    @GET("movie/order/byPopularity/")
    suspend fun getFilm():String

 }

