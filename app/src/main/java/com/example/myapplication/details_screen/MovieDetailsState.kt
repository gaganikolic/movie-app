package com.example.myapplication.details_screen

data class MovieDetailsState(
    val title: String = "",
    val overview: String? = "",
    val releaseDate: String? = "",
    val posterUrl: String? = "",
    var tagline: String? = "",
    val rating: Double = 0.0,
    val isLoading: Boolean = true
)