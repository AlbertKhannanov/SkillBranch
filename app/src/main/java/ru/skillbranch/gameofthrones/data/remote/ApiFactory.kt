package ru.skillbranch.gameofthrones.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.gameofthrones.utils.AppConfig
import java.util.concurrent.TimeUnit

object ApiFactory {

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val gameOfThronesApi: GameOfThronesApi by lazy {
        retrofit.create(GameOfThronesApi::class.java)
    }
}
