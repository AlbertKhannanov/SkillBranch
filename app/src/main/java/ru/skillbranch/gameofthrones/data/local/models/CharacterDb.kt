package ru.skillbranch.gameofthrones.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.skillbranch.gameofthrones.data.remote.responses.CharacterResponse

@Entity
data class CharacterDb(
    @PrimaryKey(autoGenerate = true)
    val characterId: Long = 0,
    val name: String,
    val titles: String,
    var aliases: String,
    val born: String,
    val died: String,
    val father: String,
    val mother: String,
    var url: String
) {

    companion object {
        fun from(character: CharacterResponse): CharacterDb =
            CharacterDb(
                name = character.name,
                titles = character.titles.joinToString(),
                aliases = character.aliases.joinToString(),
                born = character.born,
                died = character.died,
                father = character.father,
                mother = character.mother,
                url = character.url
            )

        fun fromList(characters: List<CharacterResponse>): List<CharacterDb> =
            characters.map(::from)
    }
}
