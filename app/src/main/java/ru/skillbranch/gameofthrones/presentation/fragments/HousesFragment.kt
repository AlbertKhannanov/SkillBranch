package ru.skillbranch.gameofthrones.presentation.fragments

import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.databinding.FragmentHousesBinding
import ru.skillbranch.gameofthrones.presentation.activities.MainActivity
import ru.skillbranch.gameofthrones.presentation.adapter.HousePagerAdapter
import ru.skillbranch.gameofthrones.utils.HouseType
import kotlin.math.hypot
import kotlin.math.max

class HousesFragment : Fragment() {

    private lateinit var viewModel: HousesViewModel

    private lateinit var binding: FragmentHousesBinding
    private lateinit var pagerAdapter: HousePagerAdapter

    private lateinit var colors: List<Int>
    private var currentColor: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        colors = requireContext().run {
            arrayListOf(
                getColor(R.color.stark_primary),
                getColor(R.color.lannister_primary),
                getColor(R.color.targaryen_primary),
                getColor(R.color.baratheon_primary),
                getColor(R.color.greyjoy_primary),
                getColor(R.color.martel_primary),
                getColor(R.color.tyrel_primary),
            )
        }
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

        if (currentColor != -1) binding.appbar.setBackgroundColor(currentColor)

        binding.fragmentPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabs, binding.fragmentPager) { tab, position ->
            tab.text = HouseType.values()[position].title
        }.attach()

        with(binding.tabs) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position

                    val rect = Rect()
                    val tabView = tab.view as View

                    tabView.postDelayed(
                        {
                            tabView.getGlobalVisibleRect(rect)
                            animateAppbarReval(position, rect.centerX(), rect.centerY())
                        },
                        300
                    )
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        (menu.findItem(R.id.toolbar_search).actionView as SearchView).apply {
            queryHint = "Search"
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun animateAppbarReval(position: Int, centerX: Int, centerY: Int) {
        val endRadius = max(
            hypot(centerX.toDouble(), centerY.toDouble()),
            hypot(binding.appbar.width.toDouble() - centerX.toDouble(), centerY.toDouble())
        )

        with(binding.animatedView) {
            visibility = View.VISIBLE
            setBackgroundColor(colors[position])
        }

        ViewAnimationUtils.createCircularReveal(
            binding.animatedView,
            centerX,
            centerY,
            0f,
            endRadius.toFloat()
        ).apply {
            doOnEnd {
                binding.appbar.setBackgroundColor(colors[position])
                binding.animatedView.visibility = View.INVISIBLE
            }
            start()
        }
        currentColor = colors[position]
    }
}
