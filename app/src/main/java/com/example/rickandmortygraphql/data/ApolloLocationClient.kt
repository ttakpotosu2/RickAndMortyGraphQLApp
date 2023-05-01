package com.example.rickandmortygraphql.data

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmortygraphql.LocationListQuery
import com.example.rickandmortygraphql.LocationQuery
import com.example.rickandmortygraphql.domain.locations.LocationClient
import com.example.rickandmortygraphql.domain.locations.LocationDetail
import com.example.rickandmortygraphql.domain.locations.LocationsList
import javax.inject.Inject

class ApolloLocationClient @Inject constructor(
    private val apolloClient: ApolloClient
): LocationClient{
    override suspend fun getLocations(): List<LocationsList> {
        return apolloClient.query(LocationListQuery())
            .execute()
            .data
            ?.locations
            ?.results
            ?.map { locationList ->
                LocationsList(
                    id = locationList?.id.orEmpty(),
                    name = locationList?.name.orEmpty(),
                    dimension = locationList?.dimension.orEmpty()
                )
            } ?: emptyList()
    }

    override suspend fun getLocation(id: String): LocationDetail? {
        return apolloClient.query(LocationQuery(id))
            .execute()
            .data
            ?.location
            ?.let { location ->
                LocationDetail(
                    id = location.id.orEmpty(),
                    name = location.name.orEmpty(),
                    dimension = location.dimension.orEmpty(),
                    type = location.type.orEmpty(),
                    residents = location.residents
                )
            }
    }
}