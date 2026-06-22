package br.tech.cirdan.iota.ui.feature.animes.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import br.tech.cirdan.iota.ui.feature.animes.AnimeListViewModel
import br.tech.cirdan.iota.ui.feature.common.Progress
import androidx.compose.runtime.collectAsState
import androidx.paging.LoadState
import br.tech.cirdan.iota.ui.feature.common.ErrorScreen
import br.tech.cirdan.iota.ui.feature.common.LoadAnimeError

@Composable
fun AnimeScreen(viewModel: AnimeListViewModel = hiltViewModel()) {

    val animeLazyItems = viewModel.animePagingFlow.collectAsLazyPagingItems()

    Scaffold() { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (val refreshState = animeLazyItems.loadState.refresh) {
                is LoadState.Loading -> {
                    Progress()
                }
                is LoadState.NotLoading -> {
                    if (animeLazyItems.itemCount == 0) {
                        AnimeListEmptyState()
                    } else {
                        AnimeList(animeLazyItems)
                    }
                }
                is LoadState.Error -> {
                    ErrorScreen(
                        exception = refreshState.error,
                        onRetry = { animeLazyItems.retry() },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}