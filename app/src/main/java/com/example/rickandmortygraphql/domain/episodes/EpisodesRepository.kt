package com.example.rickandmortygraphql.domain.episodes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortygraphql.data.paging.CharactersPagingSource
import com.example.rickandmortygraphql.data.paging.EpisodesPagingSource
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val episodeClient: EpisodeClient
) {

    fun execute() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            EpisodesPagingSource(episodeClient)
        }
    ).flow
//    suspend fun execute(): List<EpisodeList>{
//        return episodeClient.getEpisodes()
//    }
}