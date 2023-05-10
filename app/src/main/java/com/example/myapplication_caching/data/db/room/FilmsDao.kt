package com.example.myapplication_caching.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.domain.model.FilmsDomainModel

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films_table")
   suspend fun getFilms():List<FilmDatabaseModel>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveToDatabase(vararg films: FilmDatabaseModel)



}