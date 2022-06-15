package com.example.diploma.data.repo

import com.example.diploma.data.DiplomaApi
import com.example.diploma.data.ROADMAP_ID
import com.example.diploma.data.TECHNOLOGY_ID
import com.example.diploma.data.TOPIC_ID
import com.example.diploma.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DashboardRepository(
    private val api: DiplomaApi
) : MainRepository() {

    suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            api.getUserInfo()
        }
    }

    suspend fun getNews(): List<News?> {
        return withContext(Dispatchers.IO) {
            api.getNews()
        }
    }

    suspend fun getVacancies(): List<Vacancy?> {
        return withContext(Dispatchers.IO) {
            api.getVacancies()
        }
    }

    suspend fun getTopics(): List<Topic> {
        return withContext(Dispatchers.IO) {
            api.getTopics(
                ROADMAP_ID!!,
                TECHNOLOGY_ID!!
            )
        }
    }

    suspend fun getRoadmaps(): List<Roadmap> {
        return withContext(Dispatchers.IO) {
            api.getRoadmaps()
        }
    }

    suspend fun getTechs(): List<Technology> {
        return withContext(Dispatchers.IO) {
            api.getTechs(ROADMAP_ID!!)
        }
    }

    suspend fun getMaterial(): Material {
        return withContext(Dispatchers.IO) {
            api.getMaterial(ROADMAP_ID!!, TECHNOLOGY_ID!!, TOPIC_ID!!)
        }
    }

    suspend fun doneTopic() {
        withContext(Dispatchers.IO) {
            api.done(ROADMAP_ID!!, TECHNOLOGY_ID!!, TOPIC_ID!!)
        }
    }

}