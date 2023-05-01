package com.example.rickandmortygraphql.domain.episodes

import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeClient: EpisodeClient
){

    suspend fun execute(id: String): EpisodeDetail? {
        return episodeClient.getEpisode(id)
    }
}