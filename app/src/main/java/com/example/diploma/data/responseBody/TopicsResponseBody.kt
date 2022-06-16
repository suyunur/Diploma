package com.example.diploma.data.responseBody

import com.example.diploma.data.model.Topic
import java.io.Serializable

data class TopicsResponseBody(
    val tech_data: TechData,
    val data: List<Topic>
) : Serializable {
    data class TechData (
        val tech: String,
        val progress: Float
    )
}
