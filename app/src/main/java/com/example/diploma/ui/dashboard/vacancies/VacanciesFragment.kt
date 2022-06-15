package com.example.diploma.ui.dashboard.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.diploma.R
import com.example.diploma.data.CHOSEN_VACANCY
import com.example.diploma.data.model.Vacancy
import com.example.diploma.databinding.DiplomaFragmentVacanciesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class VacanciesFragment : Fragment(),
    VacanciesAdapter.ClickListener,
    FilterAdapter.ClickListener {

    private var _binding: DiplomaFragmentVacanciesBinding? = null
    private val binding get() = _binding!!

    private lateinit var vacanciesAdapter: VacanciesAdapter
    private lateinit var filtersAdapter: FilterAdapter

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

        viewModel.getVacancies()

        vacanciesAdapter = VacanciesAdapter(listener = this)
        filtersAdapter = FilterAdapter(listener = this)

        binding.vacanciesRecyclerView.adapter = vacanciesAdapter
        binding.filterRecyclerView.adapter = filtersAdapter

        binding.topPanel.title.text = context?.getString(R.string.vacancies)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner, vacancyObserver)
        viewModel.statusLiveData.observe(viewLifecycleOwner, statusObserver)
    }

    private val vacancyObserver = Observer<List<Vacancy?>> {
        vacanciesAdapter.setList(it)
    }

    private val statusObserver = Observer<Boolean> {
        if (it == true) {
            binding.vacanciesRecyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.vacanciesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onClick(vacancy: Vacancy) {
        CHOSEN_VACANCY = vacancy
        val dialog = VacancyDetailFragment.newInstance()
        dialog.show(parentFragmentManager, dialog::class.qualifiedName)
    }

    override fun onFilter(string: String) {
        val filteredList = viewModel.getVacanciesByFilter(string)
        filteredList?.let { vacanciesAdapter.setList(it) }
    }

}