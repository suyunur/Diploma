package com.example.diploma.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaContainerFragmentBinding
import com.example.diploma.ui.dashboard.home.HomeFragment
import com.example.diploma.ui.dashboard.mentors.MentorsFragment
import com.example.diploma.ui.dashboard.profile.ProfileFragment
import com.example.diploma.ui.dashboard.roadmap.RoadmapFragment
import com.example.diploma.ui.dashboard.vacancies.VacanciesFragment


class ContainerFragment: Fragment() {

    private lateinit var binding: DiplomaContainerFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DiplomaContainerFragmentBinding.bind(view)

        setUpBottomNavigationMenu()
    }

    private fun setUpBottomNavigationMenu() {
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val vacanciesFragment = VacanciesFragment()
        val roadmapFragment = RoadmapFragment()
        val mentorsFragment = MentorsFragment()

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
                R.id.roadmap_page -> {
                    replaceFragment(roadmapFragment)
                    true
                }
                R.id.mentors_page -> {
                    replaceFragment(mentorsFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(
            R.id.container,
            fragment
        ).commit()
    }
}