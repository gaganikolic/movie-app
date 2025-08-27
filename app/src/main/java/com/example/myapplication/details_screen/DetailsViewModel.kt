package com.example.myapplication.details_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.IMovieRepository
import kotlinx.coroutines.delay

class DetailsViewModel(
    private val repository: IMovieRepository
): ViewModel() {
    var movieDetails by mutableStateOf(MovieDetailsState())

    suspend fun loadInitData(movieId: Int) {
        movieDetails = movieDetails.copy(isLoading = true)
        delay(500)

        val movie = repository.getMovieDetails(movieId)
        movieDetails = movie.copy(isLoading = false)
    }
}
