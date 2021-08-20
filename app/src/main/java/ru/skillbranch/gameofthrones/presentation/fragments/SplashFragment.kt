package ru.skillbranch.gameofthrones.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.databinding.FragmentHouseBinding
import ru.skillbranch.gameofthrones.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
}
