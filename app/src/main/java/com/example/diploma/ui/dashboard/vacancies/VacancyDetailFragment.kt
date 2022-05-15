package com.example.diploma.ui.dashboard.vacancies

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.diploma.R
import com.example.diploma.data.CHOSEN_VACANCY
import com.example.diploma.data.COMPANY_NAMES
import com.example.diploma.data.IMAGE_INDEX
import com.example.diploma.data.VACANCY_IMAGES
import com.example.diploma.databinding.DiplomaFragmentVacancyDetailBinding


class VacancyDetailFragment: DialogFragment() {

    companion object {

        fun newInstance(): VacancyDetailFragment {
            return VacancyDetailFragment()
        }

    }

    private var _binding: DiplomaFragmentVacancyDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var requirementsAdapter: RequirementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentVacancyDetailBinding.inflate(inflater, container, false)

        requirementsAdapter = RequirementsAdapter()

        binding.topPanel.title.text = getString(R.string.job_detail)

        fillVacancyDetail()

        binding.applyButton.setOnClickListener {
            CHOSEN_VACANCY?.let { it1 -> openVacancyLink(it1.vacancyUrl) }
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun fillVacancyDetail() {
        CHOSEN_VACANCY?.apply {

            VACANCY_IMAGES[IMAGE_INDEX!!]?.let { binding.vacancyImage.setImageResource(it) }

            binding.vacancyTitle.text = title
            binding.companyName.text = COMPANY_NAMES[IMAGE_INDEX!!]
            binding.vacancySalary.text = final_salary.toString().substring(0, final_salary.toString().lastIndexOf(".")) + " KZT"
            when (jobType) {
                "INTERNSHIP" -> binding.vacancyJobType.text = "Internship"
                "FULL_TIME" -> binding.vacancyJobType.text = "Full Time"
            }

            binding.vacancyCompanyLocation.text = location

            binding.requirements.adapter = requirementsAdapter
            requirementsAdapter.setList(skill)
        }
    }

    private fun openVacancyLink(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}