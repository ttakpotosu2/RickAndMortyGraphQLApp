package com.example.rickandmortygraphql.domain.episodes

interface EpisodeClient {

    suspend fun getEpisodes(): List<EpisodeList>
    suspend fun getEpisode(id: String): EpisodeDetail?
}