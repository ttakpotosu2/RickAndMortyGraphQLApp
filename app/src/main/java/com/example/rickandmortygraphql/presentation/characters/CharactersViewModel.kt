package com.example.rickandmortygraphql.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortygraphql.domain.characters.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    private var _characters = MutableStateFlow<CharactersState>(CharactersState.Loading)
    val characters = _characters.asStateFlow()

    init {
        viewModelScope.launch {
            val result = repository.execute()
            _characters.value = CharactersState.Success(characters = result)
        }
    }
}