package com.example.rickandmortygraphql.domain.locations

import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val locationClient: LocationClient
) {

    suspend fun execute(): List<LocationsList> {
        return locationClient.getLocations()
    }
}