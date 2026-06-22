package br.tech.cirdan.iota.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.tech.cirdan.iota.ui.feature.anime.composables.AnimeDetailScreen
import br.tech.cirdan.iota.ui.feature.animes.composables.AnimeScreen
import kotlinx.serialization.Serializable

@Serializable object AnimeListScreen
@Serializable data class AnimeDetailScreen(val id: Int)


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AnimeListScreen,
    ) {
        composable<AnimeListScreen> {
            AnimeScreen(onAnimeClick = { id ->
                navController.navigate(AnimeDetailScreen(id))
            })
        }
        composable<AnimeDetailScreen> {
            AnimeDetailScreen()
        }
    }
}