package com.example.diploma.data.model

import java.io.Serializable

data class Technology(
    val id: Int?,
    val name: String?,
    val description: String?,
    val image_url: String?
) : Serializable