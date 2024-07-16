package es.leanmind.poketeam.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import es.leanmind.poketeam.movies
import es.leanmind.poketeam.ui.screens.detail.DetailScreen
import es.leanmind.poketeam.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMovieClick = {
                    navController.navigate("detail/${it.id}")
                }
            )
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = {
                    navController.popBackStack()
                }

            )
        }
    }
}
