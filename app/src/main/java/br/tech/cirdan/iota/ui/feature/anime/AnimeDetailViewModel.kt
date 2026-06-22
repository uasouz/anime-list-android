package br.tech.cirdan.iota.ui.feature.anime

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.tech.cirdan.iota.data.AnimeRepository
import br.tech.cirdan.iota.data.model.Anime
import br.tech.cirdan.iota.ui.navigation.AnimeDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)
    val anime: StateFlow<Anime?> = _anime

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        val detail = savedStateHandle.toRoute<AnimeDetailScreen>()
        fetchAnime(detail.id)
    }

    private fun fetchAnime(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _anime.value = repository.getAnimeById(id)
            _isLoading.value = false
        }
    }
}
