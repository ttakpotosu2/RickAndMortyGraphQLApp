package com.example.rickandmortygraphql.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortygraphql.domain.characters.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

   fun characters() = repository.execute().cachedIn(viewModelScope)
}