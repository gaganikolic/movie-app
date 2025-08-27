import androidx.compose.ui.graphics.Color
import com.example.myapplication.database.FavoriteMovie
import com.example.myapplication.details_screen.MovieDetailsState
import com.example.myapplication.home_screen.MovieItemState
import com.example.myapplication.model.MovieDetailsDto
import com.example.myapplication.model.MovieDto

fun MovieDto.toMovieItemState(): MovieItemState {
    return MovieItemState(
        id = id,
        badgeColor = if (adult) Color.Green else Color.Red,
        title = title,
        posterPath = "https://image.tmdb.org/t/p/w500${posterPath}",
        numberOfStartsOutOf5 = voteAverage.toInt() / 2,
        releaseYear = releaseDate.take(4)
    )
}

fun MovieDetailsDto.toMovieDetailsState(): MovieDetailsState {
    return MovieDetailsState(
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterUrl =  "https://image.tmdb.org/t/p/w500${posterPath}",
        tagline = tagline,
        rating = voteAverage
    )
}

fun MovieItemState.toFavoriteMovie():  FavoriteMovie {
    return FavoriteMovie(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500${posterPath}",
        releaseYear = releaseYear
    )
}
