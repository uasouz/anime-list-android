package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from")
    val from: br.tech.cirdan.iota.data.jikan.From,
    @SerializedName("string")
    val string: String,
    @SerializedName("to")
    val to: br.tech.cirdan.iota.data.jikan.To
)