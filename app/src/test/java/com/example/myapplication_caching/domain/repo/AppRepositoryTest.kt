package com.example.myapplication_caching.domain.repo

import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

internal class AppRepositoryTest {

    private var x by Delegates.notNull<Boolean>()

    @Before
   fun setUp(){
       x = true
   }

    @Test
    fun getDataMultiApis() {

    }

    @Test
    fun cachingData() {
    }

    @Test
    fun getFilmsFromDatabase() {
    }

    @Test
    fun getFilmTrail() {
     assertEquals(5,(3+2))
    
    }

}