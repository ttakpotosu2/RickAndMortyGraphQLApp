package com.example.rickandmortygraphql.domain.characters

interface CharacterClient {

    suspend fun getCharacters(page: Int?): List<CharacterList>
    suspend fun getCharacter(id: String): CharacterDetail?
}