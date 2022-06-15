package com.example.diploma.data.model

import java.io.Serializable

data class Material(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val subtopic: List<Subtopic>
) : Serializable {
    data class Subtopic(
        val name: String,
        val link: String,
        val type_name: String
    )
}
