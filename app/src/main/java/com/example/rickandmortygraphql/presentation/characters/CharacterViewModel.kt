package com.example.rickandmortygraphql.presentation.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.characters.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _character = MutableStateFlow<CharacterState>(CharacterState.Loading)
    val character = _character.asStateFlow()

    init{
        savedStateHandle.get<String>("characterId")?.let { character(it) }
    }

    private fun character(id: String){
        viewModelScope.launch {
            val result = characterRepository.execute(id)
            _character.value = CharacterState.Success(character = result)
        }
    }
}