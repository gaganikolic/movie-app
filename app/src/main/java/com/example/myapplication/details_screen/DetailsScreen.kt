package com.example.myapplication.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.component.MovieDesc
import com.example.myapplication.component.MovieRating
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    movieId: Int,
    modifier: Modifier = Modifier
) {
    val viewModel: DetailsViewModel = koinViewModel()

    LaunchedEffect(key1 = null) {
        viewModel.loadInitData(movieId)
    }

    if (viewModel.movieDetails.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        DetailsScreenContent(
            modifier = modifier
                .background(Color.LightGray),
            viewModel.movieDetails
        )
    }
}

@Composable
private fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    state: MovieDetailsState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = state.posterUrl,
            contentDescription = "Movie poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.ic_launcher_background)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieDesc(state)
            MovieRating(state)
        }

        Text(
            modifier = modifier
                .padding(8.dp),
            text = "${state.overview}",
            fontSize = 13.sp
        )
    }
}

@Preview
@Composable
private fun PreviewImage() {
    MyApplicationTheme {
        DetailsScreenContent(
            state = MovieDetailsState(
                title = "ABC",
                overview = "Dragana",
                releaseDate = "2025-05-05",
                posterUrl = "",
                tagline = "ABC",
                rating = 0.0
            )
        )
    }
}