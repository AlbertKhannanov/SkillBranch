package ru.skillbranch.gameofthrones.data.local.models.relations

import androidx.room.Entity

@Entity(primaryKeys = ["houseId", "characterId"])
data class HouseCharacterCrossRef(
    val houseId: Long,
    val characterId: Long
)
