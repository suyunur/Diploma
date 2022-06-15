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
) : ViewModel() {

    val newsLiveData: LiveData<List<News?>>
        get() = _newsLiveData
    private val _newsLiveData = MutableLiveData<List<News?>>()

    val loadLiveData: LiveData<Boolean>
        get() = _loadLiveData
    private val _loadLiveData = MutableLiveData<Boolean>()

    fun getNews() = viewModelScope.launch {
        _loadLiveData.value = true

        _newsLiveData.value = dashboardRepository.getNews()

        _loadLiveData.value = false
    }
}