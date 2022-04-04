package com.example.diploma.data.responseBody

import android.os.Message

data class MainResponse<out T>(
    val code: Int,
    val data: T?,
    val message: String?
)
