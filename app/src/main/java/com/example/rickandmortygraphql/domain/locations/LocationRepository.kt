package com.example.rickandmortygraphql.domain.locations

import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationClient: LocationClient
) {
    suspend fun execute(id: String): LocationDetail? {
        return locationClient.getLocation(id)
    }
}