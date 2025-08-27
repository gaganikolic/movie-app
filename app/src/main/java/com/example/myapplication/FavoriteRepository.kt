package com.example.myapplication

import com.example.myapplication.database.FavoriteMovie
import com.example.myapplication.database.FavoriteMovieDao

interface IFavoriteRepository {
    suspend fun add(movie: FavoriteMovie)
    suspend fun delete(movie: FavoriteMovie)
    suspend fun getAll(): List<FavoriteMovie>
}

class FavoriteRepository(
    private val dao: FavoriteMovieDao
): IFavoriteRepository {

    override suspend fun add(movie: FavoriteMovie) = dao.insertAll(movie)

    override suspend fun delete(movie: FavoriteMovie) = dao.delete(movie)

    override suspend fun getAll(): List<FavoriteMovie> = dao.getAll()

}