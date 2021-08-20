package ru.skillbranch.gameofthrones.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.databinding.ItemCharacterBinding
import ru.skillbranch.gameofthrones.utils.HouseType

class CharacterAdapter(
    private val houseType: HouseType,
    private val click: (CharacterDb) -> Unit
) : ListAdapter<CharacterDb, CharacterAdapter.CharacterHolder>(object :
    DiffUtil.ItemCallback<CharacterDb>() {
    override fun areItemsTheSame(oldItem: CharacterDb, newItem: CharacterDb): Boolean =
        oldItem.characterId == newItem.characterId

    override fun areContentsTheSame(oldItem: CharacterDb, newItem: CharacterDb): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val itemBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(itemBinding, houseType)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterHolder(
        private val itemBinding: ItemCharacterBinding,
        private val houseType: HouseType
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(character: CharacterDb) {
            with(itemBinding) {
                itemCharacterName.text = character.name
                itemHouseIcon.setBackgroundResource(houseType.icon)

                itemCharacterDescription.text =
                    if (character.aliases.isNotEmpty())
                        if (character.titles.isNotEmpty())
                            character.titles.replace(",", " •").plus(" • ")
                                .plus(character.aliases.replace(",", " •"))
                        else
                            character.aliases.replace(",", " •")
                    else if (character.aliases.isEmpty() && character.titles.isEmpty())
                        "Information is unknown"
                    else
                        character.titles.replace(",", " •")

                root.setOnClickListener { click(character) }
            }
        }
    }
}
