package com.example.rickandmortygraphql.domain.episodes

interface EpisodeClient {

    suspend fun getEpisodes(page: Int?): List<EpisodeList>
    suspend fun getEpisode(id: String): EpisodeDetail?
}