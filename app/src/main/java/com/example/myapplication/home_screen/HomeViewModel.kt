package com.example.myapplication.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.IFavoriteRepository
import com.example.myapplication.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toFavoriteMovie

data class UiState(
    val items: List<MovieItemState> = emptyList()
)

class HomeViewModel(
    private val movieRepository: IMovieRepository,
    private val favoriteRepository: IFavoriteRepository
) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            val result = movieRepository.getMovies()
            state = UiState(
                items = result
            )
        }
    }

    fun addToFavorites(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val current = state.items.find { it.id == movieId }
            favoriteRepository.add(
                current?.toFavoriteMovie() ?: return@launch
            )
//            favoriteRepository.delete(
//                movie.toFavoriteMovie()
//            )
        }
    }

}