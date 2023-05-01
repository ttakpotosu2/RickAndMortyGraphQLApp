package com.example.rickandmortygraphql.data

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmortygraphql.EpisodeQuery
import com.example.rickandmortygraphql.EpisodesQuery
import com.example.rickandmortygraphql.domain.episodes.EpisodeClient
import com.example.rickandmortygraphql.domain.episodes.EpisodeDetail
import com.example.rickandmortygraphql.domain.episodes.EpisodeList
import javax.inject.Inject

class ApolloEpisodeClient @Inject constructor(
    private val apolloClient: ApolloClient
): EpisodeClient {
    override suspend fun getEpisodes(): List<EpisodeList> {
        return apolloClient
            .query(EpisodesQuery())
            .execute()
            .data
            ?.episodes
            ?.results
            ?.map { episodeList ->
                EpisodeList(
                    id = episodeList?.id.orEmpty(),
                    name = episodeList?.name.orEmpty(),
                    episode = episodeList?.episode.orEmpty(),
                    airDate = episodeList?.air_date.orEmpty()
                )
            } ?: emptyList()
    }

    override suspend fun getEpisode(id: String): EpisodeDetail? {
        return apolloClient
            .query(EpisodeQuery(id))
            .execute()
            .data
            ?.episode
            ?.let { episode ->
                EpisodeDetail(
                    id = episode.id.orEmpty(),
                    name = episode.name.orEmpty(),
                    created = episode.created.orEmpty(),
                    airDate = episode.air_date.orEmpty(),
                    episode = episode.episode.orEmpty(),
                    characters = episode.characters
                )
            }
    }
}