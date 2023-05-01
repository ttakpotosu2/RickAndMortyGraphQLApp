package com.example.rickandmortygraphql.domain.episodes

import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val episodeClient: EpisodeClient
) {
    suspend fun execute(): List<EpisodeList>{
        return episodeClient.getEpisodes()
    }
}