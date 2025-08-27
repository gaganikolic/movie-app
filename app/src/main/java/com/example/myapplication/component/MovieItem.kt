package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.home_screen.MovieItemState

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    state: MovieItemState,
    onClick: () -> Unit = {},
    onFavoriteToggle: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = modifier
                .clickable {
                    onClick()
                }
        ) {
            AsyncImage(
                model = state.posterPath,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5684EE))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovieInfo(state = state)
            }
        }

        Box(
            modifier = modifier
                .padding(5.dp)
                .size(25.dp)
                .clip(CircleShape)
                .background(Color.White)
                .align(Alignment.TopEnd)
                .clickable {
                    onFavoriteToggle(state.id)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (state.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = state.title
            )
        }
    }
}


@Composable
fun StarIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Yellow
) {
    Icon(
        imageVector = Icons.Filled.Star,
        contentDescription = null,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    state: MovieItemState
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = state.title,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Text(
            text = state.releaseYear,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        )
        Row {
            repeat(5) { index ->
                StarIcon(
                    tint = if (index < state.numberOfStartsOutOf5) Color.Yellow else Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}