package ru.skillbranch.gameofthrones.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.databinding.FragmentHousesBinding
import ru.skillbranch.gameofthrones.presentation.activities.MainActivity
import ru.skillbranch.gameofthrones.presentation.adapter.HousePagerAdapter
import ru.skillbranch.gameofthrones.utils.HouseType

class HousesFragment : Fragment() {

    private lateinit var viewModel: HousesViewModel

    private lateinit var binding: FragmentHousesBinding
    private lateinit var pagerAdapter: HousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHousesBinding.inflate(inflater, container, false)

        (activity as MainActivity).also {
            viewModel = HousesViewModel(
                dataInteractor = it.dataInteractor
            )
        }

        pagerAdapter = HousePagerAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fragmentPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabs, binding.fragmentPager) { tab, position ->
            tab.text = HouseType.values()[position].title
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        (menu.findItem(R.id.toolbar_search).actionView as SearchView).apply {
            queryHint = "Search"
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}
