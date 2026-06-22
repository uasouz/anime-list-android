package br.tech.cirdan.iota.data

import androidx.paging.PagingData
import br.tech.cirdan.iota.data.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun searchAnime(): Flow<PagingData<Anime>>
}