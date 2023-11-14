package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.SearchResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getServices(): Flow<PagingData<SearchResult>>

    fun searchNews(searchQuery: String): Flow<PagingData<SearchResult>>


}