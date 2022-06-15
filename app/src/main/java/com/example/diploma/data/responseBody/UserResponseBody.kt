package com.example.diploma.data.responseBody

import com.example.diploma.data.model.User

data class UserResponseBody (
    var user: User,
    var data: AuthResponse
)