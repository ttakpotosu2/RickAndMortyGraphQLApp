package com.example.rickandmortygraphql.domain.characters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortygraphql.CharacterQuery

data class CharactersEntity(
    val id: String,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<CharacterQuery.Episode?>
)

@Entity(tableName = "characters_entity_remote_keys_table")
data class CharactersRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prev: Int?,
    val next: Int?
)