package ru.skillbranch.gameofthrones.data.local.repositories

import ru.skillbranch.gameofthrones.data.local.dao.CharacterDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseWithCharacterDao
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseCharacterCrossRef
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters

class DbRepositoryImpl(
    private val houseDao: HouseDao,
    private val characterDao: CharacterDao,
    private val houseWithCharacterDao: HouseWithCharacterDao,
) : DbRepository {

    override suspend fun isDataIsLoad(): Boolean =
        houseDao.exists()

    override suspend fun save(houseDb: HouseDb) {
        houseDao.insert(houseDb)
    }
    override suspend fun getAllHousesWithCharacters() =
        houseDao.getAllHousesWithCharacters()
    override suspend fun getHouseByNameWithCharacter(houseName: String): HouseWithCharacters =
        houseDao.getHouseByNameWithCharacters(houseName)
    override suspend fun getAllHouses(): List<HouseDb> =
        houseDao.getAllHouses()

    override suspend fun save(characterDb: CharacterDb) {
        characterDao.insert(characterDb)
    }
    override suspend fun getCharacterById(id: Long): CharacterDb =
        characterDao.getCharacterById(id)

    override suspend fun save(houseDb: HouseDb, charactersFromHouse: List<CharacterDb>) {
        val houseId = houseDao.insert(houseDb)
        charactersFromHouse.forEach { characterDb ->
        val characterId = characterDao.insert(characterDb)
            houseWithCharacterDao.insert(
                HouseCharacterCrossRef(houseId = houseId, characterId = characterId)
            )
        }
    }
}
