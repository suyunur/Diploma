package com.example.diploma.data

import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import com.example.diploma.data.responseBody.MainResponse
import com.example.diploma.data.responseBody.NewsResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface DiplomaApi {

    @POST("/register/")
    suspend fun register(@Body user: UserRequestBody): Response<MainResponse<AuthResponse?>>

    @POST("/token/")
    suspend fun login(@Body user: LoginRequestBody): Response<MainResponse<AuthResponse?>>

    @GET("/news/")
    suspend fun getNews(): Response<MainResponse<NewsResponseBody?>>

}