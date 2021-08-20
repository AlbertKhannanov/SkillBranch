package ru.skillbranch.gameofthrones.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters

@Dao
interface HouseDao: BaseDao<HouseDb> {

    @Query("SELECT * from HouseDb where EXISTS (SELECT 1 FROM HouseDb)")
    fun exists(): Boolean

    @Query("SELECT * from HouseDb")
    fun getAllHouses(): List<HouseDb>

    @Transaction
    @Query("SELECT * from HouseDb")
    fun getAllHousesWithCharacters(): List<HouseWithCharacters>

    @Transaction
    @Query("SELECT * from HouseDb where name LIKE :houseName")
    fun getHouseByNameWithCharacters(houseName: String): HouseWithCharacters

    @Query("SELECT words from HouseDb where name LIKE :houseName")
    fun getHouseWords(houseName: String): String
}
