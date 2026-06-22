package br.tech.cirdan.iota.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@JvmInline
value class Genre(val value: String)

data class Studio (
    val id: Int,
    val name: String
)

@Entity(tableName = "animes")
data class Anime (
    @PrimaryKey
    val malId: Int,

    val airing: Boolean,
    val background: String,
    val broadcast: String?,
    val duration: String,
    val episodes: Int,
    val explicitGenres: List<Genre>,
    val favorites: Int,
    val genres: List<Genre>,
    val image: String,
    val licensors: List<String>,
    val members: Int,
    val popularity: Int,
    val producers: List<String>,
    val rank: Int,
    val rating: String?,
    val score: Double,
    val scoredBy: Int,
    val season: String?,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String?,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String,
    val titleSynonyms: List<String>,
    val titles: List<String>,
    val type: String,
    val url: String,
    val year: Int
)