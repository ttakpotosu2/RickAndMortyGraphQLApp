package com.example.rickandmortygraphql.domain.locations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortygraphql.data.paging.CharactersPagingSource
import com.example.rickandmortygraphql.data.paging.LocationsPagingSource
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val locationClient: LocationClient
) {

    fun execute() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            LocationsPagingSource(locationClient)
        }
    ).flow

//    suspend fun execute(): List<LocationsList> {
//        return locationClient.getLocations()
//    }
}