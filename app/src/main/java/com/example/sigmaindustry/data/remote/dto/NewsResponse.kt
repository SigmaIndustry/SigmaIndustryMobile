package com.example.sigmaindustry.data.remote.dto

import com.example.sigmaindustry.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)