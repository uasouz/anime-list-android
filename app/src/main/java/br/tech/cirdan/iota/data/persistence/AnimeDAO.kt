package br.tech.cirdan.iota.data.persistence

import androidx.room.Dao
import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.tech.cirdan.iota.data.model.Anime

@Dao
interface AnimeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(anime: Anime)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(animeList: List<Anime>)

    @Query("SELECT * FROM animes WHERE malId = :id")
    suspend fun getAnime(id: Int): Anime?

    @Query("SELECT * FROM animes ORDER BY members DESC")
    fun searchAnime(): PagingSource<Int, Anime>

    @Query("DELETE FROM animes")
    suspend fun clearAll()
}
