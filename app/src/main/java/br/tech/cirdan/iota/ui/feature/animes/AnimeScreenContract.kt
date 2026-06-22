package br.tech.cirdan.iota.ui.feature.animes

class AnimeScreenContract {
    data class AnimeListState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}
