package com.example.myapplication.favorites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.FavoriteItem
import com.example.myapplication.database.FavoriteMovie
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: FavoritesViewModel = koinViewModel()

    FavoriteScreenContent(
        modifier = modifier,
        movies = viewModel.favorites
    )

}

@Composable
private fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    movies: FavoritesState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Favorites",
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies.items) {
                FavoriteItem(
                    modifier = modifier,
                    state = it
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewImage() {
    MyApplicationTheme {
        FavoriteScreenContent(
            movies = FavoritesState(
                listOf(
                    FavoriteMovie(
                        1,
                        "F1",
                        "",
                        "2025"
                    ),
                    FavoriteMovie(2,
                        "F2",
                        "",
                        "2025"
                    )
                )
            )
        )
    }
}