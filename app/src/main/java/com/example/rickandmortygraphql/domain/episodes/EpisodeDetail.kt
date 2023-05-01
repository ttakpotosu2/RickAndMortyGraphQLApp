package com.example.rickandmortygraphql.domain.episodes

import com.example.rickandmortygraphql.EpisodeQuery

data class EpisodeDetail(
    val id: String,
    val name: String,
    val created: String,
    val airDate: String,
    val episode: String,
    val characters: List<EpisodeQuery.Character?>
)
