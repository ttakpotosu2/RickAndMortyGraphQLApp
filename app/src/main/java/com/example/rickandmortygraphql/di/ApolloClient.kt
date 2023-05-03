package com.example.rickandmortygraphql.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.example.rickandmortygraphql.data.ApolloCharacterClient
import com.example.rickandmortygraphql.data.ApolloEpisodeClient
import com.example.rickandmortygraphql.data.ApolloLocationClient
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.episodes.EpisodeClient
import com.example.rickandmortygraphql.domain.locations.LocationClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ApolloClient {

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient{
        val sqlNormalizedCacheFactory = SqlNormalizedCacheFactory("rick_and_morty.db")

        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .normalizedCache(sqlNormalizedCacheFactory)
            .fetchPolicy(FetchPolicy.CacheFirst)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideLocationClient(apolloClient: ApolloClient): LocationClient {
        return ApolloLocationClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideEpisodeClient(apolloClient: ApolloClient): EpisodeClient {
        return ApolloEpisodeClient(apolloClient)
    }
}