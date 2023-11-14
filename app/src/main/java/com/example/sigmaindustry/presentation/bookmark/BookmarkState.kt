package com.example.sigmaindustry.presentation.bookmark

import com.example.sigmaindustry.data.remote.dto.SearchResult

data class BookmarkState(
    val articles: List<SearchResult> = emptyList()
)