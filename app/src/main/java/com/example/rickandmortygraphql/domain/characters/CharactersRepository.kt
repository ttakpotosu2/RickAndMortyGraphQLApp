package com.example.rickandmortygraphql.domain.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortygraphql.data.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepository @Inject constructor (
    private val characterClient: CharacterClient
) {

    fun execute() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            CharactersPagingSource(characterClient)
        }
    ).flow

//    suspend fun execute(): List<CharacterList> {
//        return characterClient
//            .getCharacters()
//    }
}