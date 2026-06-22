package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class AnimeList(
    @SerializedName("data")
    val `data`: List<br.tech.cirdan.iota.data.jikan.Anime>,
    @SerializedName("pagination")
    val pagination: br.tech.cirdan.iota.data.jikan.Pagination
)