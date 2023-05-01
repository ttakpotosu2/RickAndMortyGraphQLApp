package com.example.rickandmortygraphql.presentation.episodes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.episodes.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private var _episode = MutableStateFlow<EpisodeState>(EpisodeState.Loading)
    val episode = _episode.asStateFlow()

    init {
        savedStateHandle.get<String>("episodeId")?.let { episode(it) }
    }

    private fun episode(id: String){
        viewModelScope.launch {
            val result = episodeRepository.execute(id)
            _episode.value = EpisodeState.Success(episode = result)
        }
    }
}