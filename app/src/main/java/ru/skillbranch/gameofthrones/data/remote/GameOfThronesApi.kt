package ru.skillbranch.gameofthrones.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.skillbranch.gameofthrones.data.remote.responses.CharacterResponse
import ru.skillbranch.gameofthrones.data.remote.responses.HouseResponse


interface GameOfThronesApi {

    @GET("houses")
    suspend fun getHouseByName(
        @Query("name")houseName: String
    ): List<HouseResponse>

    @GET
    suspend fun getCharacterByUrl(@Url characterUrl: String): CharacterResponse
}
