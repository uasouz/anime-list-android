package br.tech.cirdan.iota.data

import br.tech.cirdan.iota.data.jikan.AnimeResponse
import br.tech.cirdan.iota.data.jikan.JikanApi
import br.tech.cirdan.iota.data.model.Anime
import br.tech.cirdan.iota.data.persistence.AnimeDAO
import br.tech.cirdan.iota.data.persistence.AppDatabase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AnimeRepositoryTest {

    private lateinit var repository: AnimeRepository
    private val database: AppDatabase = mockk()
    private val animeDao: AnimeDAO = mockk()
    private val jikanApi: JikanApi = mockk()

    @Before
    fun setup() {
        every { database.animeDao() } returns animeDao
        repository = AnimeRepositoryImpl(database, jikanApi)
    }

    @Test
    fun `getAnimeById returns local data if available`() = runTest {
        val animeId = 1
        val localAnime = mockk<Anime>()
        coEvery { animeDao.getAnime(animeId) } returns localAnime

        val result = repository.getAnimeById(animeId)

        assertEquals(localAnime, result)
        coVerify(exactly = 0) { jikanApi.getAnimeById(any()) }
    }

    @Test
    fun `getAnimeById fetches from api and saves to db if local data is not available`() = runTest {
        val animeId = 1
        val remoteAnimeDto = mockk<br.tech.cirdan.iota.data.jikan.Anime>(relaxed = true) {
            every { malId } returns animeId
            every { title } returns "Cowboy Bebop"
        }
        val animeResponse = AnimeResponse(remoteAnimeDto)
        
        coEvery { animeDao.getAnime(animeId) } returns null
        coEvery { jikanApi.getAnimeById(animeId) } returns animeResponse
        coEvery { animeDao.insert(any()) } returns Unit

        val result = repository.getAnimeById(animeId)

        coVerify { jikanApi.getAnimeById(animeId) }
        coVerify { animeDao.insert(any()) }
        assertEquals("Cowboy Bebop", result?.title)
        assertEquals(animeId, result?.malId)
    }

    @Test
    fun `getAnimeById returns null if api call fails and no local data`() = runTest {
        val animeId = 1
        coEvery { animeDao.getAnime(animeId) } returns null
        coEvery { jikanApi.getAnimeById(animeId) } throws Exception("Network error")

        val result = repository.getAnimeById(animeId)

        assertEquals(null, result)
    }
}
