package com.example.diploma.data.responseBody

import java.io.Serializable

data class AuthResponse(
    val token: String?,
    val userId: String?
): Serializable
