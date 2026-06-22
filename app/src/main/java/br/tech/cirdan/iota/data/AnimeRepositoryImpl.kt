package br.tech.cirdan.iota.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.tech.cirdan.iota.data.jikan.AnimeSearchSort
import br.tech.cirdan.iota.data.jikan.JikanApi
import br.tech.cirdan.iota.data.jikan.toDomain
import br.tech.cirdan.iota.data.model.Anime
import br.tech.cirdan.iota.data.persistence.AppDatabase
import br.tech.cirdan.iota.data.persistence.RemoteKeys
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val jikanApi: JikanApi,
    private val database: AppDatabase
): RemoteMediator<Int, Anime>() {

    override suspend fun initialize(): InitializeAction {
        // We skip the initial refresh if we already have cached data.
        // This ensures the "offline first" behavior where the app shows 
        // local data immediately and only fetches from the network if 
        // the cache is empty or the user manually refreshes.
        return if (database.remoteKeysDao().count() > 0) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Anime>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val response = jikanApi.searchAnime(page = page, sort = AnimeSearchSort.DESC)
            val endOfPaginationReached = !response.pagination.hasNextPage

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.animeDao().clearAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = response.data.map {
                    RemoteKeys(itemId = it.malId, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAll(keys)
                database.animeDao().insertAll(response.data.map { it.toDomain() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { anime ->
                database.remoteKeysDao().remoteKeysId(anime.malId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { anime ->
                database.remoteKeysDao().remoteKeysId(anime.malId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.malId?.let { animeId ->
                database.remoteKeysDao().remoteKeysId(animeId)
            }
        }
    }

}

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val jikanApi: JikanApi
): AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun searchAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            remoteMediator = AnimeRemoteMediator(jikanApi,database),
            pagingSourceFactory = { database.animeDao().searchAnime() }
        ).flow
    }

    override suspend fun getAnimeById(id: Int): Anime? {
        val localAnime = database.animeDao().getAnime(id)
        if (localAnime != null) return localAnime

        return try {
            val remoteAnime = jikanApi.getAnimeById(id).data.toDomain()
            database.animeDao().insert(remoteAnime)
            remoteAnime
        } catch (e: Exception) {
            null
        }
    }
}
