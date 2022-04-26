package com.example.diploma.data.repo

import com.example.diploma.data.DiplomaApi
import com.example.diploma.data.model.News
import com.example.diploma.data.model.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DashboardRepository(
    private val api: DiplomaApi
): MainRepository() {

    suspend fun getNews(): Result<List<News>?> {
        return withContext(Dispatchers.IO) {
            api.getNews()
        }
    }

    suspend fun getVacancies(): List<Vacancy?> {
        return withContext(Dispatchers.IO) {
            api.getVacancies()
        }
    }

}