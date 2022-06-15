package com.example.diploma.data.model

import java.io.Serializable

data class Topic(
    val id: Int?,
    val name: String?,
    val description: String?,
    val is_done: Boolean?
) : Serializable
