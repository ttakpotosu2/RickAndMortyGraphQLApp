package com.example.rickandmortygraphql.presentation.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.episodes.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodesRepository
): ViewModel() {

    private var _episodes = MutableStateFlow<EpisodesState>(EpisodesState.Loading)
    val episodes = _episodes.asStateFlow()

    init {
        viewModelScope.launch {
            val result = repository.execute()
            _episodes.value = EpisodesState.Success(episodes = result)
        }
    }
}