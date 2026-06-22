package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("items")
    val items: br.tech.cirdan.iota.data.jikan.Items,
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int
)