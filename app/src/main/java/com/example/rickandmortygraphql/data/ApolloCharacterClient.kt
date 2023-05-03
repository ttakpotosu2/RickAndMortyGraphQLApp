package com.example.rickandmortygraphql.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.rickandmortygraphql.CharacterListQuery
import com.example.rickandmortygraphql.CharacterQuery
import com.example.rickandmortygraphql.domain.characters.CharacterClient
import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList
import javax.inject.Inject

class ApolloCharacterClient @Inject constructor(
    private val apolloClient: ApolloClient
): CharacterClient {
    override suspend fun getCharacters(page: Int?): List<CharacterList> {
        return apolloClient
            .query(CharacterListQuery(Optional.present(page)))
            .execute()
            .data
            ?.characters
            ?.results
            ?.map {characterList ->
                CharacterList(
                    id = characterList?.id.orEmpty(),
                    name = characterList?.name.orEmpty(),
                    image = characterList?.image.orEmpty(),
                )
            } ?: emptyList()
    }

    override suspend fun getCharacter(id: String): CharacterDetail? {
        return apolloClient
            .query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.let {character ->
                CharacterDetail(
                    id = character.id.orEmpty(),
                    name = character.name.orEmpty(),
                    image = character.image.orEmpty(),
                    status = character.status.orEmpty(),
                    species = character.species.orEmpty(),
                    gender = character.gender.orEmpty(),
                    episode = character.episode
                )
            }
    }
}