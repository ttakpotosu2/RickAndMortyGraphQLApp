package com.example.rickandmortygraphql.presentation.locations

import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.locations.LocationDetail
import com.example.rickandmortygraphql.domain.locations.LocationsList

sealed class LocationsState {
    object Loading: LocationsState()
    data class Success(val locations: List<LocationsList>): LocationsState()
//    class Error(val message: String? = null) : LocationsState()
}

sealed class LocationState {
    object Loading: LocationState()
    data class Success(val location: LocationDetail?): LocationState()
    class Error(val message: String? = null) : LocationState()
}
