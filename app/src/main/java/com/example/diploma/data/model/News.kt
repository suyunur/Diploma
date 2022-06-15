package com.example.diploma.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    val title: String?,
    val body: String?,
    @SerializedName("link")
    val link: String?
) : Serializable
