package com.example.rickandmortygraphql.domain.characters

import javax.inject.Inject

class CharactersRepository @Inject constructor (
    private val characterClient: CharacterClient
) {

    suspend fun execute(): List<CharacterList> {
        return characterClient
            .getCharacters()
    }
}