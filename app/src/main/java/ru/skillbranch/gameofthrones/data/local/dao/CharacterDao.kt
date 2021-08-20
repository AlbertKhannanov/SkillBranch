package ru.skillbranch.gameofthrones.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb

@Dao
interface CharacterDao: BaseDao<CharacterDb> {

    @Query("SELECT * from CharacterDb where characterId = :id limit 1")
    suspend fun getCharacterById(id: Long): CharacterDb
}
