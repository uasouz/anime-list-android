package br.tech.cirdan.iota.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.tech.cirdan.iota.ui.feature.animes.composables.AnimeScreen
import kotlinx.serialization.Serializable

@Serializable object AnimeListScreen
@Serializable object AnimeDetailScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AnimeListScreen,
    ) {
        composable<AnimeListScreen> {
            AnimeScreen()
        }
    }
}