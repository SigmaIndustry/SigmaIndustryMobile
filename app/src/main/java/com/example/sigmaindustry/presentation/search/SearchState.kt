package com.example.sigmaindustry.presentation.search

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.SearchResult

import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<SearchResult>>? = null
)