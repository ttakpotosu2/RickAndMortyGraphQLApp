package com.example.rickandmortygraphql.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortygraphql.domain.characters.CharacterDetail
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.domain.characters.CharactersEntity
import com.example.rickandmortygraphql.domain.characters.CharactersRemoteKeys

@Database(
    entities = [
        CharacterList::class,
        CharactersRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun charactersRemoteKeysDao(): CharactersRemoteKeysDao
}