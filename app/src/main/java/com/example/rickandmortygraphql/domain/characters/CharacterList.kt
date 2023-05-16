package com.example.rickandmortygraphql.domain.characters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortygraphql.CharacterListQuery

@Entity("characters_entity_table")
data class CharacterList(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<CharacterListQuery.Episode>
)
