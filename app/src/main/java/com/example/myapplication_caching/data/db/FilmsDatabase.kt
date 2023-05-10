package com.example.myapplication_caching.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.data.db.room.FilmsDao
import com.example.myapplication_caching.domain.model.FilmsDomainModel

@Database(entities = [FilmDatabaseModel::class], version = 1, exportSchema = false)
abstract class FilmsDatabase() : RoomDatabase() {

    abstract val filmsDao :FilmsDao
}

lateinit var INSTANCE :FilmsDatabase


fun getDataBase(context: Context):FilmsDatabase{

    synchronized(FilmsDatabase::class){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context,FilmsDatabase::class.java,"films_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        }
    }
    return INSTANCE
}