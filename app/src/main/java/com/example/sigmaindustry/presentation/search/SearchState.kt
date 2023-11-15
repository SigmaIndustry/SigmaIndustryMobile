package com.example.sigmaindustry.presentation.search

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.Service

import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val services: Flow<PagingData<Service>>? = null
)