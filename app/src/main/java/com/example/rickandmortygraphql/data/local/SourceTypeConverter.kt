package com.example.rickandmortygraphql.data.local

import androidx.room.TypeConverter
import com.example.rickandmortygraphql.CharacterListQuery
import com.example.rickandmortygraphql.CharacterQuery
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceTypeConverter {

    @TypeConverter
    fun fromCharacterQuery(source: List<CharacterListQuery.Episode>): String{
        return Json.encodeToString(source)
    }

    @TypeConverter
    fun toCharacterQuery(source: String): List<CharacterListQuery.Episode> {
        return Json.decodeFromString(source)
    }
}