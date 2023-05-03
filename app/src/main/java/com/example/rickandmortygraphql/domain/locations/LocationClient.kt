package com.example.rickandmortygraphql.domain.locations

interface LocationClient {

    suspend fun getLocations(page: Int?): List<LocationsList>
    suspend fun getLocation(id: String): LocationDetail?
}