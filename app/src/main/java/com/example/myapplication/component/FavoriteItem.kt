package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.database.FavoriteMovie
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    state: FavoriteMovie
) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        AsyncImage(
            model = state.posterUrl,
            contentDescription = "Image",
            modifier = modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.ic_launcher_background)
        )

        Row (
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF5684EE))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FavoriteInfo(state = state)
        }
    }
}

@Composable
private fun FavoriteInfo(
    modifier: Modifier = Modifier,
    state: FavoriteMovie
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
            fontSize = 16.sp
        )
        Text(
            text = state.releaseYear,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        )
    }
}

@Composable
@Preview
private fun PreviewImage() {
    MyApplicationTheme {
        FavoriteItem(
            state = FavoriteMovie(1, "F1", "", "2025")
        )
    }
}