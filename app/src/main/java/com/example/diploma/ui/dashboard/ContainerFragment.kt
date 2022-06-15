package com.example.diploma.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.diploma.R
import com.example.diploma.data.LAST_PAGE
import com.example.diploma.databinding.DiplomaContainerFragmentBinding
import com.example.diploma.ui.dashboard.home.HomeFragment
import com.example.diploma.ui.dashboard.profile.ProfileFragment
import com.example.diploma.ui.dashboard.roadmap.RoadmapFragment
import com.example.diploma.ui.dashboard.vacancies.VacanciesFragment


class ContainerFragment : Fragment() {

    private var _binding: DiplomaContainerFragmentBinding? = null
    private val binding get() = _binding!!

    private var lastPage = 0

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var vacanciesFragment: VacanciesFragment
    private lateinit var roadmapFragment: RoadmapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaContainerFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.itemIconTintList = null

        binding.bottomNavigation.selectedItemId = R.id.vacancies_page

        setUpBottomNavigationMenu()
    }

    private fun setUpBottomNavigationMenu() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        vacanciesFragment = VacanciesFragment()
        roadmapFragment = RoadmapFragment()

        replaceFragment(vacanciesFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    lastPage = 1
                    replaceFragment(homeFragment)
                    true
                }
                R.id.profile_page -> {
                    lastPage = 2
                    replaceFragment(profileFragment)
                    true
                }
                R.id.vacancies_page -> {
                    lastPage = 3
                    replaceFragment(vacanciesFragment)
                    true
                }
                R.id.roadmap_page -> {
                    lastPage = 4
                    replaceFragment(roadmapFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(
                R.id.container,
                fragment,
                fragment.tag
            )
            addToBackStack(null)
        }
    }

    override fun onResume() {
        super.onResume()

        if (LAST_PAGE == 4) {
            binding.bottomNavigation.selectedItemId = R.id.roadmap_page
        }
    }
}