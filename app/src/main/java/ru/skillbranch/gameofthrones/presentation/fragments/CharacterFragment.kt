package ru.skillbranch.gameofthrones.presentation.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
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

        binding.toolbarHint.setBackgroundResource(HouseType.fromString(args.houseName).primaryColor)

        binding.houseImage.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.houseImage.setImageResource(HouseType.fromString(args.houseName).coastOfArms)

        viewModel.getHouseWords(args.houseName)
        viewModel.getCharacter(args.characterId)

        viewModel.character.observe(viewLifecycleOwner) {
            Log.i("asdfasdf", "${it}")
            with(binding) {
                characterName.text = it.name
                tvDataAliases.text = it.aliases
                tvDataBorn.text = it.born
                tvDataTitles.text = it.titles
                if (it.father == "") {
                    tvFather.isVisible = false
                    tvDataFather.isVisible = false
                } else {
                    tvDataFather.text = it.father
                }
                if (it.mother == "") {
                    tvMother.isVisible = false
                    tvDataMother.isVisible = false
                } else {
                    tvDataMother.text = it.mother
                }
            }
        }

        viewModel.words.observe(viewLifecycleOwner) {
            binding.tvDataWords.text = it
        }

    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
