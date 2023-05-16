package com.example.rickandmortygraphql.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortygraphql.domain.characters.CharactersRemoteKeys

@Dao
interface CharactersRemoteKeysDao {
    @Query("SELECT * FROM characters_entity_remote_keys_table WHERE id = :id")
    fun getCharactersRemoteKeys(id: String): CharactersRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharactersRemoteKeys(remoteKeys: List<CharactersRemoteKeys>)

    @Query("DELETE FROM characters_entity_remote_keys_table")
    suspend fun deleteCharactersRemoteKeys()
}