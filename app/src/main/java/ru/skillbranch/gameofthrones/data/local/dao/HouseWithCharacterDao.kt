package ru.skillbranch.gameofthrones.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseCharacterCrossRef

@Dao
interface HouseWithCharacterDao {

    @Insert
    suspend fun insert(houseCharacterCrossRef: HouseCharacterCrossRef)

    @Transaction
    @Query("SELECT * from HouseDb")
    fun getHousesWithCharacters(): List<HouseWithCharacters>

}
