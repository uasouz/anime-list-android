package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class Aired(
    @SerializedName("from")
    val from: String,
    @SerializedName("prop")
    val prop: br.tech.cirdan.iota.data.jikan.Prop,
    @SerializedName("to")
    val to: String
)