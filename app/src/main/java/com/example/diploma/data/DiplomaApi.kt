package com.example.diploma.data

import com.example.diploma.data.model.*
import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.requestBody.VacancyRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import com.example.diploma.data.responseBody.RegisterResponse
import com.example.diploma.data.responseBody.TopicsResponseBody
import retrofit2.http.*


interface DiplomaApi {

    @POST("register/")
    suspend fun register(@Body user: UserRequestBody): RegisterResponse?

    @POST("token/")
    suspend fun login(@Body user: LoginRequestBody): AuthResponse?

    @GET("recs/")
    suspend fun getNews(): List<News?>

    @GET("vacancies/")
    suspend fun getVacancies(): List<Vacancy?>

    @PUT("vacancies/")
    suspend fun updateVacancies(@Body data: VacancyRequestBody): List<Vacancy?>

    @GET("me/")
    suspend fun getUserInfo(): User

    @GET("roadmaps/")
    suspend fun getRoadmaps(): List<Roadmap>

    @GET("roadmaps/{spec_id}/{tech_id}/")
    suspend fun getTopics(
        @Path("spec_id") roadmapId: Int,
        @Path("tech_id") techId: Int
    ): TopicsResponseBody

    @GET("roadmaps/{spec_id}/")
    suspend fun getTechs(
        @Path("spec_id") roadmapId: Int
    ): List<Technology>

    @GET("roadmaps/{spec_id}/{tech_id}/{topic_id}")
    suspend fun getMaterial(
        @Path("spec_id") roadmapId: Int,
        @Path("tech_id") techId: Int,
        @Path("topic_id") topicId: Int
    ): Material

    @GET("roadmaps/{spec_id}/{tech_id}/{topic_id}/done/")
    suspend fun done(
        @Path("spec_id") roadmapId: Int,
        @Path("tech_id") techId: Int,
        @Path("topic_id") topicId: Int
    )
}