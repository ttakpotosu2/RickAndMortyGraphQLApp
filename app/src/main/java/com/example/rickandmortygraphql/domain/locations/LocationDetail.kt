package com.example.rickandmortygraphql.domain.locations

import com.example.rickandmortygraphql.LocationQuery

data class LocationDetail (
    val id: String,
    val name: String,
    val dimension: String,
    val type: String,
    val residents: List<LocationQuery.Resident?>
)
