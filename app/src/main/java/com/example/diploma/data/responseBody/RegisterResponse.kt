package com.example.diploma.data.responseBody

import com.example.diploma.data.model.User

data class RegisterResponse (
    var user: User,
    var data: AuthResponse
)