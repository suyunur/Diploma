package com.example.diploma.data.repo

import com.example.diploma.data.DiplomaApi
import com.example.diploma.data.responseBody.NewsResponseBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DashboardRepository(
    private val api: DiplomaApi
): MainRepository() {

    suspend fun getNews(): Result<NewsResponseBody?> = withContext(Dispatchers.IO) {
        safeApiCall { api.getNews() }
    }

}