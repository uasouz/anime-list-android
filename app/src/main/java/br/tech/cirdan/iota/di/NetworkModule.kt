package br.tech.cirdan.iota.di

import br.tech.cirdan.iota.data.jikan.JikanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJikanRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JikanApi.Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesJikanApiService(retrofit: Retrofit): JikanApi {
        return retrofit.create(JikanApi::class.java)
    }
}