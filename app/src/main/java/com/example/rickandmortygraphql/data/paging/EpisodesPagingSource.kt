package com.example.rickandmortygraphql.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.episodes.EpisodeClient
import com.example.rickandmortygraphql.domain.episodes.EpisodeList

class EpisodesPagingSource(
    private val episodeClient: EpisodeClient
): PagingSource<Int, EpisodeList>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeList> {
        return try {
            val page = params.key ?: 1
            val response = episodeClient.getEpisodes(page = page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}