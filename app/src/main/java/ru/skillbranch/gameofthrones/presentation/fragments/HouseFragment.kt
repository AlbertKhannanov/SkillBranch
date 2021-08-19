package ru.skillbranch.gameofthrones.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.databinding.FragmentHouseBinding
import ru.skillbranch.gameofthrones.presentation.activities.MainActivity
import ru.skillbranch.gameofthrones.presentation.adapter.CharacterAdapter
import ru.skillbranch.gameofthrones.utils.AppConfig.HOUSE_NAME
import ru.skillbranch.gameofthrones.utils.HouseType

class HouseFragment : Fragment() {

    private lateinit var viewModel: HouseViewModel

    private lateinit var binding: FragmentHouseBinding
    private lateinit var houseName: String
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        arguments?.getString(HOUSE_NAME)?.let {
            houseName = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHouseBinding.inflate(inflater, container, false)

        (activity as MainActivity).also {
            viewModel = HouseViewModel(
                dataInteractor = it.dataInteractor
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharacterAdapter(HouseType.fromString(houseName)) {
            (activity as MainActivity).navController
                .navigate(HousesFragmentDirections.actionHousesFragmentToCharacterFragment(it.characterId, houseName))
        }
        binding.rvCharacters.adapter = characterAdapter

        viewModel.loadHouse(houseName)

        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.submitList(it)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchView: SearchView = menu.findItem(R.id.toolbar_search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getCharactersLikeName(newText ?: "")
                return true
            }
        })
    }

    companion object {
        fun newInstance(houseName: String): HouseFragment =
            HouseFragment().apply {
                arguments = bundleOf(HOUSE_NAME to houseName)
            }
    }
}
