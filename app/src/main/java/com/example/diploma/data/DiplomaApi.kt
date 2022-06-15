package com.example.diploma.data

import com.example.diploma.data.model.*
import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface DiplomaApi {

    @POST("register/")
    suspend fun register(@Body user: UserRequestBody): AuthResponse?

    @POST("token/")
    suspend fun login(@Body user: LoginRequestBody): AuthResponse?

    @GET("recs/")
    suspend fun getNews(): List<News?>

    @GET("vacancies/")
    suspend fun getVacancies(): List<Vacancy?>

    @GET("me/")
    suspend fun getUserInfo(): User

    @GET("roadmaps/")
    suspend fun getRoadmaps(): List<Roadmap>

    @GET("roadmaps/{spec_id}/{tech_id}/")
    suspend fun getTopics(
        @Path("spec_id") roadmapId: Int,
        @Path("tech_id") techId: Int
    ): List<Topic>

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