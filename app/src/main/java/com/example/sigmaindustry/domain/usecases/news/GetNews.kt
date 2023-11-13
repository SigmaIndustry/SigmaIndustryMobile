package com.example.sigmaindustry.domain.usecases.news

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<PagingData<SearchResult>> {
        return newsRepository.getServices()
    }
}