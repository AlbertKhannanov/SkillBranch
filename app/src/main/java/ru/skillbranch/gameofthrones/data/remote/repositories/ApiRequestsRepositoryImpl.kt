package ru.skillbranch.gameofthrones.data.remote.repositories

import ru.skillbranch.gameofthrones.data.remote.GameOfThronesApi
import ru.skillbranch.gameofthrones.data.remote.responses.CharacterResponse
import ru.skillbranch.gameofthrones.data.remote.responses.HouseResponse

class ApiRequestsRepositoryImpl(
    private val api: GameOfThronesApi
) : ApiRequestsRepository {

    override suspend fun loadHouseByName(houseName: String): List<HouseResponse> =
        api.getHouseByName(houseName)

    override suspend fun loadCharacterByUrl(characterUrl: String): CharacterResponse =
        api.getCharacterByUrl(characterUrl)

    override suspend fun loadCharactersByUrls(characterUrls: List<String>): List<CharacterResponse> {
        val result = arrayListOf<CharacterResponse>()
        characterUrls.forEach {
            result.add(api.getCharacterByUrl(it))
        }
        return result
    }
}
