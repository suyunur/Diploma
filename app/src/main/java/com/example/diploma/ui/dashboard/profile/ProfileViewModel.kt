package com.example.diploma.ui.dashboard.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.data.model.User
import com.example.diploma.data.repo.DashboardRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    val loadLiveData: LiveData<Boolean>
        get() = _loadLiveData
    private val _loadLiveData = MutableLiveData<Boolean>()

    val userLiveData: LiveData<User>
        get() = _userLiveData
    private val _userLiveData = MutableLiveData<User>()

    fun getUser() {
        viewModelScope.launch {
            _loadLiveData.value = true

            _userLiveData.value = dashboardRepository.getUser()

            _loadLiveData.value = false
        }
    }

}