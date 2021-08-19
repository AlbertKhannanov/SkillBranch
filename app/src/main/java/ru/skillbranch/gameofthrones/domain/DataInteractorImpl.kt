package ru.skillbranch.gameofthrones.domain

import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseWithCharacters
import ru.skillbranch.gameofthrones.data.local.repositories.DbRepository
import ru.skillbranch.gameofthrones.data.remote.repositories.ApiRequestsRepository


class DataInteractorImpl(
    private val dbRepository: DbRepository,
    private val apiRequestsRepository: ApiRequestsRepository,
) : DataInteractor {

    override suspend fun loadData(houseName: String) {
        val houseResponse = apiRequestsRepository.loadHouseByName(houseName).first()
        val charactersListResponse = apiRequestsRepository.loadCharactersByUrls(houseResponse.swornMembers)

        dbRepository.save(HouseDb.from(houseResponse), CharacterDb.fromList(charactersListResponse))
    }

    override suspend fun getAllHousesWithCharacters(): List<HouseWithCharacters> =
        dbRepository.getAllHousesWithCharacters()
    override suspend fun getHouseByNameWithCharacters(houseName: String): HouseWithCharacters =
        dbRepository.getHouseByNameWithCharacter(houseName)

    override suspend fun getAllHouses(): List<HouseDb> =
        dbRepository.getAllHouses()

    override suspend fun getCharacterById(id: Long): CharacterDb =
        dbRepository.getCharacterById(id)

    override suspend fun isDataIsLoad(): Boolean = dbRepository.isDataIsLoad()
}
