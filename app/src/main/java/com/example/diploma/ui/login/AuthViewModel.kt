package com.example.diploma.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.data.repo.AuthRepository
import com.example.diploma.data.requestBody.LoginRequestBody
import com.example.diploma.data.requestBody.UserRequestBody
import com.example.diploma.data.responseBody.AuthResponse
import kotlinx.coroutines.launch


class AuthViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {

    val authLiveData: LiveData<AuthResponse>
        get() = _authLiveData
    private val _authLiveData = MutableLiveData<AuthResponse>()

    val loadLiveData: LiveData<Boolean>
        get() = _loadLiveData
    private val _loadLiveData = MutableLiveData<Boolean>()

    fun register(
        name: String,
        surname: String,
        email: String,
        phoneNumber: String,
        password: String
    ) = viewModelScope.launch {
        _loadLiveData.value = true

        val response = authRepository.register(
            UserRequestBody(
                name = name,
                surname = surname,
                email = email,
                phone = phoneNumber,
                password = password
            )
        )
        _authLiveData.value = response.getOrNull()

        _authLiveData.value?.let { saveAuthResponse(it) }

        _loadLiveData.value = false
    }

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loadLiveData.value = true

        authRepository.login(
            LoginRequestBody(
                email = email,
                password = password
            )
        )

        _loadLiveData.value = false
    }

    private fun saveAuthResponse(response: AuthResponse) {
        authRepository.saveAuthResponse(response)
    }
}