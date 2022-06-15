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
) : ViewModel() {

    val vacanciesLiveData: LiveData<List<Vacancy?>>
        get() = _vacanciesLiveData
    private val _vacanciesLiveData = MutableLiveData<List<Vacancy?>>()

    val statusLiveData: LiveData<Boolean>
        get() = _statusLiveData
    private val _statusLiveData = MutableLiveData<Boolean>()

    fun getVacancies() = viewModelScope.launch {
        _statusLiveData.value = true

        _vacanciesLiveData.value = dashboardRepository.getVacancies()

        _statusLiveData.value = false
    }

    fun getVacanciesByFilter(filter: String): List<Vacancy?>? {
        val vacancies = _vacanciesLiveData.value

        return when (filter) {

            "Internship" -> {
                vacancies?.filter {
                    it?.employment_type == "INTERNSHIP"
                }
            }

            "Full Time" -> {
                vacancies?.filter {
                    it?.employment_type == "FULL_TIME"
                }
            }

            "Part Time" -> {
                vacancies?.filter {
                    it?.employment_type == "PART TIME"
                }
            }

            "Project Work" -> {
                vacancies?.filter {
                    it?.employment_type == "PROJECT WORK"
                }
            }

            "Volunteering" -> {
                vacancies?.filter {
                    it?.employment_type == "Volunteering".uppercase()
                }
            }

            else -> vacancies
        }
    }

}