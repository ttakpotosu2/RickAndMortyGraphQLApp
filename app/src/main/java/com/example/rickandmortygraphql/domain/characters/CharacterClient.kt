package com.example.rickandmortygraphql.domain.characters

interface CharacterClient {

    suspend fun getCharacters(): List<CharacterList>
    suspend fun getCharacter(id: String): CharacterDetail?
}