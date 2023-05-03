package com.example.rickandmortygraphql.presentation.characters

import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList

sealed class CharactersState {
    object Loading: CharactersState()
    data class Success(val characters: List<CharacterList>): CharactersState()
    class Error(val message: String? = null) : CharactersState()
}

sealed class CharacterState {
    object Loading: CharacterState()
    data class Success(val character: CharacterDetail?): CharacterState()
    class Error(val message: String? = null) : CharacterState()
}
