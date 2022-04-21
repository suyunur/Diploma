package com.example.diploma.ui.dashboard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.data.model.News
import com.example.diploma.data.repo.DashboardRepository
import kotlinx.coroutines.launch


class HomeViewModel(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    val newsLiveData: LiveData<List<News?>>
        get() = _newsLiveData
    private val _newsLiveData = MutableLiveData<List<News?>>()

    fun getNews() = viewModelScope.launch {
        _newsLiveData.value = dashboardRepository.getNews().getOrNull()
    }
}