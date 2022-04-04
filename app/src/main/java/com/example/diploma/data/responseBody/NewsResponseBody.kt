package com.example.diploma.data.responseBody

import com.example.diploma.data.model.News
import java.io.Serializable

data class NewsResponseBody(
    val news: List<News?>
) : Serializable
