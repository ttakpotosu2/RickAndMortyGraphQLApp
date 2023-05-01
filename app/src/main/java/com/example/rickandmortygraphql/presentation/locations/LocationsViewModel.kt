package com.example.rickandmortygraphql.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.locations.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationsRepository
): ViewModel(){

    private var _locations = MutableStateFlow<LocationsState>(LocationsState.Loading)
    val locations = _locations.asStateFlow()

    init {
        viewModelScope.launch {
            val result = repository.execute()
            _locations.value = LocationsState.Success(locations = result)
        }
    }
}