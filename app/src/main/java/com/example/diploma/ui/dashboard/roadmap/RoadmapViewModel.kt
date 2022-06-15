package com.example.diploma.ui.dashboard.roadmap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.data.model.Material
import com.example.diploma.data.model.Roadmap
import com.example.diploma.data.model.Technology
import com.example.diploma.data.model.Topic
import com.example.diploma.data.repo.DashboardRepository
import kotlinx.coroutines.launch

class RoadmapViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    val roadmapLiveData: LiveData<List<Roadmap>>
        get() = _roadmapLiveData
    private val _roadmapLiveData = MutableLiveData<List<Roadmap>>()

    val techsLiveData: LiveData<List<Technology>>
        get() = _techsLiveData
    private val _techsLiveData = MutableLiveData<List<Technology>>()

    val topicsLiveData: LiveData<List<Topic>>
        get() = _topicsLiveData
    private val _topicsLiveData = MutableLiveData<List<Topic>>()

    val loadLiveData: LiveData<Boolean>
        get() = _loadLiveData
    private val _loadLiveData = MutableLiveData<Boolean>()

    val materialLiveData: LiveData<Material>
        get() = _materialLiveData
    private val _materialLiveData = MutableLiveData<Material>()

    val doneLiveData: LiveData<Any?>
        get() = _doneLiveData
    private val _doneLiveData = MutableLiveData<Any?>()

    fun getTopics() = viewModelScope.launch {
        _loadLiveData.value = true

        _topicsLiveData.value = dashboardRepository.getTopics()

        _loadLiveData.value = false
    }

    fun getRoadmaps() = viewModelScope.launch {
        _loadLiveData.value = true

        _roadmapLiveData.value = dashboardRepository.getRoadmaps()

        _loadLiveData.value = false
    }

    fun getTechs() = viewModelScope.launch {
        _loadLiveData.value = true

        _techsLiveData.value = dashboardRepository.getTechs()

        _loadLiveData.value = false
    }

    fun getMaterial() = viewModelScope.launch {
        _loadLiveData.value = true

        _materialLiveData.value = dashboardRepository.getMaterial()

        _loadLiveData.value = false
    }

    fun doneTopic() = viewModelScope.launch {
        _loadLiveData.value = true

        dashboardRepository.doneTopic()

        _loadLiveData.value = false
    }

}