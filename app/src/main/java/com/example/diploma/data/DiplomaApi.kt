package com.example.diploma.data

import com.example.diploma.data.model.News
import com.example.diploma.data.model.User
import com.example.diploma.data.model.Vacancy
import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import com.example.diploma.data.responseBody.MainResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface DiplomaApi {

    @POST("register/")
    suspend fun register(@Body user: UserRequestBody)

    @POST("token/")
    suspend fun login(@Body user: LoginRequestBody): AuthResponse?

    @GET("recs/")
    suspend fun getNews(): Result<List<News>?>

    @GET("vacancies/")
    suspend fun getVacancies(): List<Vacancy?>

    @GET("me/")
    suspend fun getUserInfo(): User

}