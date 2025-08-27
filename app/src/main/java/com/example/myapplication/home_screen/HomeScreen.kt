package com.example.myapplication.home_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainActivity
import com.example.myapplication.component.MovieItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onMovieItemClick: (Int) -> Unit
) {
    val actvity = LocalContext.current as MainActivity
    val homeViewModel: HomeViewModel = koinViewModel()

    HomeScreenContent(
        modifier = modifier,
        state = homeViewModel.state,
        onMovieItemClick = {
            Toast.makeText(
                actvity,
                "$it",
                Toast.LENGTH_SHORT
            ).show()
            onMovieItemClick(it)
        },
        onFavoriteToggle = {movieId ->
            homeViewModel.addToFavorites(movieId)
        }
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: UiState,
    onMovieItemClick: (Int) -> Unit = {},
    onFavoriteToggle: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(state.items) {
            MovieItem(
                state = it,
                onClick = {
                    onMovieItemClick(it.id)
                },
                onFavoriteToggle = onFavoriteToggle
            )
        }
    }
}

@Preview
@Composable
private fun PreviewImage() {
    MyApplicationTheme {
        HomeScreenContent(
            state = UiState(
                items = listOf(
                    MovieItemState(
                        title = "War of the Worlds",
                        posterPath = "https://image.tmdb.org/t/p/w500/x6xiixuQ3FpbEgiu8cr1444H0g7.jpg",
                        numberOfStartsOutOf5 = 5,
                        releaseYear = "2005",
                        ),
                    MovieItemState(
                        title = "War of the Worlds",
                        posterPath = "https://image.tmdb.org/t/p/w500/x6xiixuQ3FpbEgiu8cr1444H0g7.jpg",
                        numberOfStartsOutOf5 = 0,
                        releaseYear = "2005"
                    ),
                    MovieItemState(
                        title = "War of the Worlds Dragana Dragana Dragana",
                        posterPath = "https://image.tmdb.org/t/p/w500/x6xiixuQ3FpbEgiu8cr1444H0g7.jpg",
                        numberOfStartsOutOf5 = 3,
                        releaseYear = "2005"
                    ),
                    MovieItemState(
                        title = "",
                        posterPath = "https://image.tmdb.org/t/p/w500/x6xiixuQ3FpbEgiu8cr1444H0g7.jpg",
                        numberOfStartsOutOf5 = 3,
                        releaseYear = "2005"
                    )
                )
            ),
            onFavoriteToggle = {state ->

            }
        )
    }
}
