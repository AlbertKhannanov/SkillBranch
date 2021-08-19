package ru.skillbranch.gameofthrones.data.local.repositories

import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters

interface DbRepository {

    suspend fun isDataIsLoad(): Boolean

    suspend fun save(houseDb: HouseDb)
    suspend fun getAllHousesWithCharacters(): List<HouseWithCharacters>
    suspend fun getHouseByNameWithCharacter(houseName: String): HouseWithCharacters
    suspend fun getAllHouses(): List<HouseDb>

    suspend fun save(characterDb: CharacterDb)
    suspend fun getCharacterById(id: Long): CharacterDb

    suspend fun save(houseDb: HouseDb, charactersFromHouse: List<CharacterDb>)
}
