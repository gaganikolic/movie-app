package com.example.myapplication.home_screen

import androidx.compose.ui.graphics.Color

data class MovieItemState(
    val id: Int = 0,
    val badgeColor: Color = Color.Companion.Green,
    val title: String = "",
    val posterPath: String = "",
    val numberOfStartsOutOf5: Int = 0,
    val releaseYear: String = "",
    val isFavorite: Boolean = false
)