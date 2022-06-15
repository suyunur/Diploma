package com.example.diploma.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vacancy(
    val id: Int?,
    val title: String?,
    val description: String?,
    val final_salary: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("employment_type")
    val employment_type: String?,
    val schedule: String?,
    val specialization: String?,
    val location: String?,
    @SerializedName("skill")
    val skill: List<Skill>,
    val link: String,
    val employer: String,
    val is_favourite: Boolean?
) : Serializable {

    data class Skill(
        var name: String?
    )
}
