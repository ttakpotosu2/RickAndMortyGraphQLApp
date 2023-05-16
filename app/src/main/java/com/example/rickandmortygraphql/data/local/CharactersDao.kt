package com.example.rickandmortygraphql.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters_entity_table")
    fun getCharacters(): PagingSource<Int, CharacterList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterList>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacters(updateIds: List<CharacterList>)

    @Query("DELETE FROM characters_entity_table")
    suspend fun deleteCharacters()

    @Query("SELECT * FROM characters_entity_table WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterList
}