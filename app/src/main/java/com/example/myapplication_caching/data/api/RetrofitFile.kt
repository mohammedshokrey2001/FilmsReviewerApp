package com.example.myapplication_caching.data.api

import com.example.myapplication_caching.BuildConfig
import com.example.myapplication_caching.utilites.Utilities
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Utilities.BASE_URL_TDMB)
    .build()

private val retrofit2 = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Utilities.BASE_URL_MINI_MOVIE)
    .build()

object AppApi {

    val filmsApiTMDB : FilmsApiTDMB by  lazy {
        retrofit.create(FilmsApiTDMB::class.java)
    }
    val filmsApiMiniMovie : FilmsApiMinMovie by  lazy {
        retrofit2.create(FilmsApiMinMovie::class.java)
    }


}


