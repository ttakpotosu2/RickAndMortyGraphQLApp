package com.example.rickandmortygraphql.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.characters.CharacterList

class CharactersPagingSource(
    private val characterClient: CharacterClient
): PagingSource<Int, CharacterList>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterList> {
        return try {
            val page = params.key ?: 1
            val response = characterClient.getCharacters(page = page)

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