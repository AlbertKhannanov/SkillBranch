package ru.skillbranch.gameofthrones.data.local.models.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb

data class HouseWithCharacters(
    @Embedded
    var housue: HouseDb,
    @Relation(
        parentColumn = "houseId",
        entityColumn = "characterId",
        associateBy = Junction(HouseCharacterCrossRef::class)
    )
    var characters: List<CharacterDb>
)
