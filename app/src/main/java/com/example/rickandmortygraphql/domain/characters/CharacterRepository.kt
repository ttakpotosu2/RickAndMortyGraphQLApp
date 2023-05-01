package com.example.rickandmortygraphql.domain.characters

import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterClient: CharacterClient
) {

    suspend fun execute(id: String): CharacterDetail? {
        return characterClient
            .getCharacter(id)
    }
}