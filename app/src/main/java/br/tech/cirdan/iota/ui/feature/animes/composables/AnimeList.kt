package br.tech.cirdan.iota.ui.feature.animes.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.tech.cirdan.iota.data.model.Anime
import br.tech.cirdan.iota.ui.feature.common.ErrorItem

@Composable
fun AnimeList(
    animeList: LazyPagingItems<Anime>,
    onAnimeClick: (Int) -> Unit
) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(animeList.itemCount) { index ->
                val anime = animeList[index]
                if (anime != null) {
                    Box(modifier = Modifier.clickable { onAnimeClick(anime.malId) }) {
                        AnimeListItem(anime)
                    }
                }
            }

            when (val appendState = animeList.loadState.append) {
                is LoadState.Loading -> {
                    item { AnimeLoadingItem() }
                }
                is LoadState.Error -> {
                    // Infinite scroll failed. Show a retry item row at the bottom.
                    item {
                        ErrorItem(
                            exception = appendState.error,
                            onRetry = { animeList.retry() }
                        )
                    }
                }
                is LoadState.NotLoading -> { /* Do nothing */ }
            }
        }
}