package ru.skillbranch.gameofthrones.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.domain.DataInteractor

class CharacterViewModel(
    private val dataInteractor: DataInteractor
): ViewModel() {

    val character: MutableLiveData<CharacterDb> = MutableLiveData()

    fun getCharacter(characterId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            character.postValue(
                dataInteractor.getCharacterById(characterId)
            )
        }
    }
}
