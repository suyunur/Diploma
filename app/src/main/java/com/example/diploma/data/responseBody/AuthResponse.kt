package com.example.diploma.data.responseBody

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthResponse(
    @SerializedName("refresh")
    val refresh: String?,
    @SerializedName("access")
    val access: String?
): Serializable
