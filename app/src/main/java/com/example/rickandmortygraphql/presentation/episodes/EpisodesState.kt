package com.example.rickandmortygraphql.presentation.episodes

import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.episodes.EpisodeDetail
import com.example.rickandmortygraphql.domain.episodes.EpisodeList
import com.example.rickandmortygraphql.domain.locations.LocationDetail
import com.example.rickandmortygraphql.domain.locations.LocationsList
import com.example.rickandmortygraphql.presentation.locations.LocationState
import com.example.rickandmortygraphql.presentation.locations.LocationsState

sealed class EpisodesState {
    object Loading: EpisodesState()
    data class Success(val episodes: List<EpisodeList>): EpisodesState()
//    class Error(val message: String? = null) : EpisodesState()
}

sealed class EpisodeState {
    object Loading: EpisodeState()
    data class Success(val episode: EpisodeDetail?): EpisodeState()
    class Error(val message: String? = null) : EpisodeState()
}
