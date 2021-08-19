package ru.skillbranch.gameofthrones.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.skillbranch.gameofthrones.data.remote.responses.HouseResponse

@Entity
data class HouseDb(
    @PrimaryKey(autoGenerate = true)
    val houseId: Long = 0,
    val name: String,
    val words: String
) {

    companion object {
        val empty = HouseDb(-1, "Alba", "best")

        fun from(house: HouseResponse): HouseDb =
            HouseDb(
                name = house.name,
                words = house.words
            )

        fun fromList(characters: List<HouseResponse>): List<HouseDb> =
            characters.map(::from)
    }
}
