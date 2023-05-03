package com.example.rickandmortygraphql.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.locations.LocationClient
import com.example.rickandmortygraphql.domain.locations.LocationsList

class LocationsPagingSource(
    private val locationClient: LocationClient
): PagingSource<Int, LocationsList>() {
    override fun getRefreshKey(state: PagingState<Int, LocationsList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsList> {
        return try {
            val page = params.key ?: 1
            val response = locationClient.getLocations(page = page)

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