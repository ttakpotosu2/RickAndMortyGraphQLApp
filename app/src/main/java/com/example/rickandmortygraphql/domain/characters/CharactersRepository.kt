package com.example.rickandmortygraphql.domain.characters

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortygraphql.data.local.AppDatabase
import com.example.rickandmortygraphql.data.paging.CharacterRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val database: AppDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun execute(): Flow<PagingData<CharacterList>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { database.charactersDao().getCharacters() },
        remoteMediator = CharacterRemoteMediator(
            client = characterClient,
            database = database
        )
    ).flow
}