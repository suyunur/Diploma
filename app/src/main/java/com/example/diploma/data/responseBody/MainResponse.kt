package com.example.diploma.data.responseBody

data class MainResponse<out T>(
    val code: Int,
    val data: T?,
    val message: String?
)
