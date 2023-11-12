package com.example.sigmaindustry.presentation.bookmark

import com.example.sigmaindustry.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)