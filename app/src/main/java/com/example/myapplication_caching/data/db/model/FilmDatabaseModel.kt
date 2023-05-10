package com.example.myapplication_caching.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("films_table")
data class FilmDatabaseModel(val movieName: String,@PrimaryKey val id: Int, val popularity: Int)