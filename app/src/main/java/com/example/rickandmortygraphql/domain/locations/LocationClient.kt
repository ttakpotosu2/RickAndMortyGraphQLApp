package com.example.rickandmortygraphql.domain.locations

interface LocationClient {

    suspend fun getLocations(): List<LocationsList>
    suspend fun getLocation(id: String): LocationDetail?
}