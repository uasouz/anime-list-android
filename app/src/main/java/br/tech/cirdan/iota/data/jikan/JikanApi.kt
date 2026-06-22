package br.tech.cirdan.iota.data.jikan

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

enum class AnimeSearchOrder(val value: String) {
    MAL_ID("mal_id"),
    TITLE("title"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    EPISODES("episodes"),
    SCORE("score"),
    SCORED_BY("scored_by"),
    RANK("rank"),
    POPULARITY("popularity"),
    MEMBERS("members"),
    FAVORITES("favorites");


    override fun toString(): String = value
}

enum class AnimeSearchSort(val value: String) {
    DESC("desc"),
    ASC("asc");

    override fun toString(): String = value
}

interface JikanApi {

    object Endpoints {
        const val BASE_URL = "https://api.jikan.moe/v4/"
        const val SEARCH_ANIME = "anime"
    }

    @GET(Endpoints.SEARCH_ANIME)
    suspend fun searchAnime(
        @Query("page") page: Int = 1,
        @Query("order_by") orderBy: AnimeSearchOrder = AnimeSearchOrder.MEMBERS,
        @Query("sort") sort: AnimeSearchSort = AnimeSearchSort.DESC,
        @Query("sfw") sfw: Boolean = true
        ): AnimeList

    @GET("${Endpoints.SEARCH_ANIME}/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): AnimeResponse
}