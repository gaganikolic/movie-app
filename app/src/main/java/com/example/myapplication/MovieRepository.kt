@file:SuppressLint("UnsafeOptInUsageError")

package com.example.myapplication

import android.annotation.SuppressLint
import com.example.myapplication.details_screen.MovieDetailsState
import com.example.myapplication.home_screen.MovieItemState
import com.example.myapplication.model.MovieDetailsDto
import com.example.myapplication.model.PopularMoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import toMovieDetailsState
import toMovieItemState

interface IMovieRepository {
    suspend fun getMovies(): List<MovieItemState>
    suspend fun getMovieDetails(id: Int): MovieDetailsState
}


class MovieRepository(
    private val client: HttpClient
): IMovieRepository {

    override suspend fun getMovies(): List<MovieItemState> {
        val movies = client.get("movie/popular").body<PopularMoviesResponse>()
        return movies.results.map { movie ->
            movie.toMovieItemState()
        }
    }

    override suspend fun getMovieDetails(id: Int): MovieDetailsState {
        val movie = client.get("movie/$id").body<MovieDetailsDto>()
        return movie.toMovieDetailsState()
    }

}