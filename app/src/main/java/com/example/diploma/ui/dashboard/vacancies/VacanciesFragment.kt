package com.example.diploma.ui.dashboard.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.data.model.Vacancy
import com.example.diploma.databinding.DiplomaFragmentVacanciesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class VacanciesFragment: Fragment(),
    VacanciesAdapter.ClickListener {

    private var _binding: DiplomaFragmentVacanciesBinding? = null
    private val binding get() = _binding!!

    private lateinit var vacanciesAdapter: VacanciesAdapter

    private val viewModel: VacanciesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentVacanciesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vacanciesAdapter = VacanciesAdapter(listener = this, null)
        binding.vacanciesRecyclerView.adapter = vacanciesAdapter

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner, vacancyObserver)
    }

    private val vacancyObserver = Observer<List<Vacancy?>> {
        vacanciesAdapter.submitList(it)
    }

    override fun onClick(vacancy: Vacancy) {
        val action = VacanciesFragmentDirections.actionVacanciesToDetail(
            vacancy = vacancy
        )
        findNavController().navigate(action)
    }

}