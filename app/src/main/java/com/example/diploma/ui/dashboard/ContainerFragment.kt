package com.example.diploma.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaContainerFragmentBinding
import com.example.diploma.ui.dashboard.home.HomeFragment
import com.example.diploma.ui.dashboard.profile.ProfileFragment
import com.example.diploma.ui.dashboard.vacancies.VacanciesFragment


class ContainerFragment: Fragment() {

    private var _binding: DiplomaContainerFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var vacanciesFragment: VacanciesFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaContainerFragmentBinding.inflate(inflater, container, false)

        setUpBottomNavigationMenu()

        return binding.root
    }

    private fun setUpBottomNavigationMenu() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        vacanciesFragment = VacanciesFragment()

        replaceFragment(homeFragment)
        binding.bottomNavigation.selectedItemId = R.id.home_page

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.profile_page -> {
                    replaceFragment(profileFragment)
                    true
                }
                R.id.vacancies_page -> {
                    replaceFragment(vacanciesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(
            R.id.container,
            fragment,
            fragment.tag
        ).commit()
    }
}