package com.example.diploma.ui.dashboard.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaFragmentVacancyDetailBinding


class VacancyDetailFragment: Fragment() {

    private var _binding: DiplomaFragmentVacancyDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var requirementsAdapter: RequirementsAdapter

    private val args: VacancyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentVacancyDetailBinding.inflate(inflater, container, false)

        requirementsAdapter = RequirementsAdapter()

        binding.topPanel.title.text = getString(R.string.vacancies)

        fillVacancyDetail()

        return binding.root
    }

    private fun fillVacancyDetail() {
        args.vacancy.apply {

            Glide
                .with(binding.vacancyImage.context)
                .load(imageUrl)
                .centerCrop()
                .into(binding.vacancyImage)

            binding.vacancyTitle.text = title
            binding.companyName.text = companyName
            binding.vacancySalary.text = salary.toString()
            binding.vacancyJobType.text = jobType
            binding.vacancyCompanyLocation.text = location

            binding.requirements.adapter = requirementsAdapter
            requirementsAdapter.submitList(requirements)
        }
    }

}