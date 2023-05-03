package com.example.rickandmortygraphql.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.characters.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

   fun characters(): Flow<PagingData<CharacterList>> = repository
       .execute()
       .cachedIn(viewModelScope)

//    private var _characters = MutableStateFlow<CharactersState>(CharactersState.Loading)
//    val characters = _characters.asStateFlow()
//
//    init {
//        viewModelScope.launch {
//            val result = repository.execute()
//            _characters.value = CharactersState.Success(characters = result)
//        }
//    }
}