package ru.skillbranch.gameofthrones.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.domain.DataInteractor
import java.util.*

class HouseViewModel(
    private val dataInteractor: DataInteractor
) : ViewModel() {

    private var _characters: List<CharacterDb> = arrayListOf()
    var characters: MutableLiveData<List<CharacterDb>> = MutableLiveData()

    fun loadHouse(houseName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _characters = dataInteractor.getHouseByNameWithCharacters(houseName).characters
            characters.postValue(_characters)
        }
    }

    fun getCharactersLikeName(characterName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (characterName.isNotEmpty())
                characters.postValue(
                    _characters.filter { it.name.lowercase().startsWith(characterName.lowercase()) }
                )
            else
                characters.postValue(_characters)
        }
    }
}
