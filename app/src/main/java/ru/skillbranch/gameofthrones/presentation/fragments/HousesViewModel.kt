package ru.skillbranch.gameofthrones.presentation.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.domain.DataInteractor

class HousesViewModel(
    private val dataInteractor: DataInteractor
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}
