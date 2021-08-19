package ru.skillbranch.gameofthrones.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.skillbranch.gameofthrones.presentation.fragments.HouseFragment
import ru.skillbranch.gameofthrones.utils.HouseType

class HousePagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = HouseType.values().size

    override fun createFragment(position: Int): Fragment =
        HouseFragment.newInstance(HouseType.values()[position].fullTitle)
}
