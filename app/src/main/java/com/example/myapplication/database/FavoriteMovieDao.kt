package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movie")
    fun getAll(): List<FavoriteMovie>

    @Insert
    fun insertAll(vararg movie: FavoriteMovie)

    @Delete
    fun delete(movie: FavoriteMovie)
}