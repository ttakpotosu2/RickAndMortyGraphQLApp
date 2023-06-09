package com.example.rickandmortygraphql.domain.characters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortygraphql.CharacterQuery


data class CharacterDetail(
    val id: String,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<CharacterQuery.Episode?>
)
