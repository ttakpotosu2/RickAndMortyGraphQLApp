package com.example.rickandmortygraphql.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortygraphql.data.local.AppDatabase
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.characters.CharactersRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val client: CharacterClient,
    private val database: AppDatabase
): RemoteMediator<Int, CharacterList>(){

    private val charactersDao = database.charactersDao()
    private val charactersRemoteKeysDao = database.charactersRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterList>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prev
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.next
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = client.getCharacters(currentPage)

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            val endOfPaginationReached = response.isEmpty()

            database.withTransaction {
                if(loadType == LoadType.REFRESH){
                    charactersDao.deleteCharacters()
                    charactersRemoteKeysDao.deleteCharactersRemoteKeys()
                }
                val keys = response.map { characterList ->
                    CharactersRemoteKeys(
                        id = characterList.id,
                        prev = prevPage,
                        next = nextPage
                    )
                }
                charactersRemoteKeysDao.addCharactersRemoteKeys(remoteKeys = keys)
                charactersDao.addCharacters(characters = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }
    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterList>
    ): CharactersRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                charactersRemoteKeysDao.getCharactersRemoteKeys(id = it)
            }
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterList>
    ): CharactersRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { charactersRemoteKeysDao.getCharactersRemoteKeys(id = it.id) }
    }

    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterList>
    ): CharactersRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { charactersRemoteKeysDao.getCharactersRemoteKeys(id = it.id) }
    }
}