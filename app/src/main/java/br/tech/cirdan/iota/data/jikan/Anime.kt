package br.tech.cirdan.iota.data.jikan


import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("aired")
    val aired: br.tech.cirdan.iota.data.jikan.Aired,
    @SerializedName("airing")
    val airing: Boolean,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("background")
    val background: String,
    @SerializedName("broadcast")
    val broadcast: br.tech.cirdan.iota.data.jikan.Broadcast,
    @SerializedName("demographics")
    val demographics: List<br.tech.cirdan.iota.data.jikan.Demographic>,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("episodes")
    val episodes: Int,
    @SerializedName("explicit_genres")
    val explicitGenres: List<br.tech.cirdan.iota.data.jikan.ExplicitGenre>,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("genres")
    val genres: List<br.tech.cirdan.iota.data.jikan.Genre>,
    @SerializedName("images")
    val images: br.tech.cirdan.iota.data.jikan.Images,
    @SerializedName("licensors")
    val licensors: List<br.tech.cirdan.iota.data.jikan.Licensor>,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("producers")
    val producers: List<br.tech.cirdan.iota.data.jikan.Producer>,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("score")
    val score: Double,
    @SerializedName("scored_by")
    val scoredBy: Int,
    @SerializedName("season")
    val season: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("studios")
    val studios: List<br.tech.cirdan.iota.data.jikan.Studio>,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("themes")
    val themes: List<br.tech.cirdan.iota.data.jikan.Theme>,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_english")
    val titleEnglish: String,
    @SerializedName("title_japanese")
    val titleJapanese: String,
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerializedName("titles")
    val titles: List<br.tech.cirdan.iota.data.jikan.Title>,
    @SerializedName("trailer")
    val trailer: br.tech.cirdan.iota.data.jikan.Trailer,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: Int
)


fun br.tech.cirdan.iota.data.jikan.Anime.toDomain() = br.tech.cirdan.iota.data.model.Anime(
    airing = this.airing,
    background = this.background,
    broadcast = this.broadcast.string,
    duration = this.duration,
    episodes = this.episodes,
    explicitGenres = this.explicitGenres.map {
        br.tech.cirdan.iota.data.model.Genre(it.name)
    },
    favorites = this.favorites,
    genres = this.genres.map {
        br.tech.cirdan.iota.data.model.Genre(it.name)
    },
    image = this.images.webp.imageUrl,
    licensors = this.licensors.map { it.name },
    malId = this.malId,
    members = this.members,
    popularity = this.popularity,
    producers = this.producers.map { it.name },
    rank = this.rank,
    rating = this.rating,
    score = this.score,
    scoredBy = this.scoredBy,
    season = this.season,
    source = this.source,
    status = this.status,
    studios = this.studios.map {
        br.tech.cirdan.iota.data.model.Studio(it.malId,it.name)
    },
    synopsis = this.synopsis,
    title = this.title,
    titleEnglish = this.titleEnglish,
    titleJapanese = this.titleJapanese,
    titleSynonyms = this.titleSynonyms,
    titles = this.titles.map { it.title },
    type = this.type,
    url = this.url,
    year = this.year
)