package com.example.diploma.ui.dashboard.vacancies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.data.model.Vacancy
import com.example.diploma.data.repo.DashboardRepository
import kotlinx.coroutines.launch

class VacanciesViewModel(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    val vacanciesLiveData: LiveData<List<Vacancy?>>
        get() = _vacanciesLiveData
    private val _vacanciesLiveData = MutableLiveData<List<Vacancy?>>()

    fun getVacancies() = viewModelScope.launch {

        _vacanciesLiveData.value = dashboardRepository.getVacancies()

    }

}