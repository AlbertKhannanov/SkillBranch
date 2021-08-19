package ru.skillbranch.gameofthrones.presentation.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.skillbranch.gameofthrones.data.local.AppDatabase
import ru.skillbranch.gameofthrones.data.local.dao.CharacterDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseWithCharacterDao
import ru.skillbranch.gameofthrones.domain.DataInteractor
import ru.skillbranch.gameofthrones.utils.AppConfig
import ru.skillbranch.gameofthrones.utils.LoadResult

class MainViewModel(
    private val dataInteractor: DataInteractor,
) : ViewModel() {

    val result: MutableLiveData<LoadResult<Boolean>> = MutableLiveData(LoadResult.Loading(false))

    fun syncData(isConnected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val isDbEmpty: Boolean = !dataInteractor.isDataIsLoad()
            if (isDbEmpty) {
                if (!isConnected) {
                    result.postValue(LoadResult.Error("Интернет недоступен - приложение не может быть запущенно. Подключитесь к интернету и перезапустите приложение"))
                    return@launch
                }
                loadData()
                result.postValue(LoadResult.Success(true))
            } else {
//                delay(5000)
                result.postValue(LoadResult.Success(true))
            }
        }
    }

    private suspend fun loadData() {
        AppConfig.NEED_HOUSES.forEach { houseName ->
            dataInteractor.loadData(houseName)
        }
    }
}
