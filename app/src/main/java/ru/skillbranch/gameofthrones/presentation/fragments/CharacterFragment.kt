package ru.skillbranch.gameofthrones.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.navigation.fragment.navArgs
import ru.skillbranch.gameofthrones.databinding.FragmentCharacterBinding
import ru.skillbranch.gameofthrones.presentation.activities.MainActivity
import ru.skillbranch.gameofthrones.utils.HouseType

class CharacterFragment : Fragment() {

    private lateinit var viewModel: CharacterViewModel

    private lateinit var binding: FragmentCharacterBinding
    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        (activity as MainActivity).also {
            viewModel = CharacterViewModel(
                dataInteractor = it.dataInteractor
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.houseImage.setBackgroundResource(HouseType.fromString(args.houseName).coastOfArms)

        viewModel.getCharacter(args.characterId)

        viewModel.character.observe(viewLifecycleOwner) {
            with(binding) {
                characterName.text = it.name
                tvDataWords.text = ""
                tvDataAliases.text = it.aliases
                tvDataBorn.text = it.born
                tvDataTitles.text = it.titles
                tvDataFather.text = it.father
                tvDataMother.text = it.mother
            }
        }

    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
