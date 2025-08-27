package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.component.MovieItem
import com.example.myapplication.details_screen.DetailsScreen
import com.example.myapplication.favorites_screen.FavoritesScreen
import com.example.myapplication.home_screen.HomeScreen
import com.example.myapplication.home_screen.MovieItemState
import com.example.myapplication.ui.theme.MyApplicationTheme

sealed class NavDestination(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home: NavDestination(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    object Favorites: NavDestination(
        title = "Favorites",
        route = "favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    )
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val items = listOf(
                NavDestination.Home,
                NavDestination.Favorites
            )

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            var selectedItemIndex by remember { mutableStateOf(0) }

                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if(selectedItemIndex == index) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.route) {
//                                            popUpTo(navController.graph.startDestinationId) {
//                                                saveState = true
//                                            }
//                                            launchSingleTop = true
//                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "home"
                    ){
                        composable("home") {
                            HomeScreen(
                                modifier = Modifier.fillMaxSize(),
                                {
                                    navController.navigate(route = "details/$it")
                                }
                            )
                        }
                        composable("details/{movieId}") {backStackEntry ->
                            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: return@composable
                            DetailsScreen(
                                movieId = movieId,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        composable("favorites") {
                            FavoritesScreen(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageHeaderExample() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            ) {

            }
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text("ca")
                Text("caoo")
            }
        }

        Text("Feb 21")
    }
}

@Preview
@Composable
fun PreviewImage() {
    MyApplicationTheme {
        MovieItem(
            state = MovieItemState(
                title = "War of the Worlds",
                posterPath = "https://image.tmdb.org/t/p/w500/x6xiixuQ3FpbEgiu8cr1444H0g7.jpg",
                numberOfStartsOutOf5 = 3,
                releaseYear = "2005"
            ),
            onFavoriteToggle = {state  ->

            }
        )
    }
}