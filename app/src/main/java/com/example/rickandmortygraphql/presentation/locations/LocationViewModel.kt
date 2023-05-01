package com.example.rickandmortygraphql.presentation.locations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.locations.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private var _location = MutableStateFlow<LocationState>(LocationState.Loading)
    val location = _location.asStateFlow()

    init{
        savedStateHandle.get<String>("locationId")?.let { location(it) }
    }

    private fun location(id: String){
        viewModelScope.launch {
            val result = locationRepository.execute(id)
            _location.value = LocationState.Success(location = result)
        }
    }
}