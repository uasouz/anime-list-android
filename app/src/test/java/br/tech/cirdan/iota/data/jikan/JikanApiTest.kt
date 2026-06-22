package br.tech.cirdan.iota.data.jikan

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JikanApiTest {

    private lateinit var api: JikanApi

    @Before
    fun setup() {
        api = Retrofit.Builder()
            .baseUrl(JikanApi.Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JikanApi::class.java)
    }

    @Test
    fun `searchAnime returns data from real API`() = runTest {
        val response = api.searchAnime(page = 1)
        
        assertNotNull(response.data)
        assertTrue(response.data.isNotEmpty())
    }

    @Test
    fun `getAnimeById returns data from real API`() = runTest {
        val response = api.getAnimeById(1) // Cowboy Bebop
        
        assertEquals("Cowboy Bebop", response.data.title)
        assertEquals(1, response.data.malId)
    }
}
