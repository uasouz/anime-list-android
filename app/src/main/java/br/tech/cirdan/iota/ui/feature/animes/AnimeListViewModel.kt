package br.tech.cirdan.iota.ui.feature.animes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.tech.cirdan.iota.data.AnimeRepository
import br.tech.cirdan.iota.data.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
): ViewModel() {



    private val _uiState = MutableStateFlow(AnimeScreenContract.AnimeListState())
    val uiState: StateFlow<AnimeScreenContract.AnimeListState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val animePagingFlow = animeRepository.searchAnime().cachedIn(viewModelScope)
}