package com.example.myapplication.favorites_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.IFavoriteRepository
import com.example.myapplication.database.FavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class FavoritesState(
    val items: List<FavoriteMovie> = emptyList()
)

class FavoritesViewModel(
    private val favoriteRepository: IFavoriteRepository
): ViewModel() {
    var favorites by mutableStateOf(FavoritesState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = favoriteRepository.getAll()
            favorites = favorites.copy(
                items = result
            )
        }
    }

}