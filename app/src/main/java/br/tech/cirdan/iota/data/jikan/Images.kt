package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg")
    val jpg: br.tech.cirdan.iota.data.jikan.Jpg,
    @SerializedName("webp")
    val webp: br.tech.cirdan.iota.data.jikan.Webp
)