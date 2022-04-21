package com.example.diploma.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vacancy(
    val title: String?,
    val description: String?,
    val salary: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("employment_type")
    val jobType: String?,
    val schedule: String?,
    val specialization: String?,
    val location: String?,
    val skill: List<Skill>
): Serializable
