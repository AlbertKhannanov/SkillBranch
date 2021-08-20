package ru.skillbranch.gameofthrones.data.remote.repositories

import ru.skillbranch.gameofthrones.data.remote.responses.CharacterResponse
import ru.skillbranch.gameofthrones.data.remote.responses.HouseResponse

interface ApiRequestsRepository {

    suspend fun loadHouseByName(houseName: String): List<HouseResponse>

    suspend fun loadCharacterByUrl(characterUrl: String): CharacterResponse

    suspend fun loadCharactersByUrls(characterUrls: List<String>): List<CharacterResponse>
}
