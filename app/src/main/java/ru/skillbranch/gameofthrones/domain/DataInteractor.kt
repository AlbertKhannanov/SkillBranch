package ru.skillbranch.gameofthrones.domain

import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters

interface DataInteractor {

    // Internet requests
    suspend fun loadData(houseName: String)

    // Db requests
    suspend fun getAllHousesWithCharacters(): List<HouseWithCharacters>
    suspend fun getHouseByNameWithCharacters(houseName: String): HouseWithCharacters
    suspend fun getAllHouses(): List<HouseDb>
    suspend fun getCharacterById(id: Long): CharacterDb
    suspend fun getHouseWords(houseName: String): String
    suspend fun isDataIsLoad(): Boolean
}
