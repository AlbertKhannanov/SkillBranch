package ru.skillbranch.gameofthrones.presentation.activities

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.AppDatabase
import ru.skillbranch.gameofthrones.data.local.repositories.DbRepositoryImpl
import ru.skillbranch.gameofthrones.data.remote.ApiFactory
import ru.skillbranch.gameofthrones.data.remote.repositories.ApiRequestsRepositoryImpl
import ru.skillbranch.gameofthrones.databinding.ActivityMainBinding
import ru.skillbranch.gameofthrones.domain.DataInteractor
import ru.skillbranch.gameofthrones.domain.DataInteractorImpl
import ru.skillbranch.gameofthrones.presentation.fragments.SplashFragmentDirections
import ru.skillbranch.gameofthrones.utils.LoadResult

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    lateinit var dataInteractor: DataInteractor
    lateinit var viewModel: MainViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLateinitFields()
        navController =
            (supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment).navController

        viewModel.syncData(isConnected())

        viewModel.result.observe(this) {
            when (it) {
                is LoadResult.Loading ->
                    navController.navigate(R.id.nav_splash_fragment)
                is LoadResult.Success ->
                    navController.navigate(SplashFragmentDirections.actionSplashFragmentToHousesFragment())
                is LoadResult.Error ->
                    Snackbar.make(
                        binding.root,
                        it.errorMessage.toString(),
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
            }
        }
    }

    private fun initLateinitFields() {
        database = AppDatabase(applicationContext)
        initDataInteractor()
        initViewModel()
    }

    private fun initDataInteractor() {
        dataInteractor = DataInteractorImpl(
            dbRepository = DbRepositoryImpl(
                houseDao = database.getHouseDao(),
                characterDao = database.getCharacterDao(),
                houseWithCharacterDao = database.getHouseWithCharacterDao(),
            ),
            apiRequestsRepository = ApiRequestsRepositoryImpl(
                api = ApiFactory.gameOfThronesApi
            )
        )
    }

    private fun initViewModel() {
        viewModel = MainViewModel(
            dataInteractor = dataInteractor,
        )
    }

    private fun isConnected(): Boolean {
        val cm = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
