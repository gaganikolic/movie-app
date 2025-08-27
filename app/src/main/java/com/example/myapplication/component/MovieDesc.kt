package com.example.myapplication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.details_screen.MovieDetailsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDesc(
    state: MovieDetailsState
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = state.title,
                maxLines = 2,
                minLines = 2,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Text(
                text = "${state.tagline}",
                fontSize = 16.sp
            )

            Text(
                text = "${state.releaseDate}",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun MovieRating(
    state: MovieDetailsState
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Yellow
        )
        Text(
            text = "${state.rating}",
            fontSize = 16.sp
        )
    }
}