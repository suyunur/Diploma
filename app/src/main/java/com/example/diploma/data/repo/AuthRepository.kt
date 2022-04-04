package com.example.diploma.data.repo

import android.content.Context
import android.content.SharedPreferences
import com.example.diploma.R
import com.example.diploma.data.DiplomaApi
import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthRepository(
    private val api: DiplomaApi,
    private val sharedPreferences: SharedPreferences,
    private val context: Context
): MainRepository() {

    suspend fun register(user: UserRequestBody): Result<AuthResponse?> = withContext(Dispatchers.IO) {
        safeApiCall { api.register(user) }
    }

    suspend fun login(user: LoginRequestBody): Result<AuthResponse?> = withContext(Dispatchers.IO) {
        safeApiCall { api.login(user) }
    }

    fun saveAuthResponse(response: AuthResponse) {
        sharedPreferences.edit().putString(context.getString(R.string.auth_token), response.token).apply()
        sharedPreferences.edit().putString(context.getString(R.string.user_id), response.userId).apply()
    }
}