package com.example.sigmaindustry.domain.usecases.news

import com.example.sigmaindustry.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
//    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<SearchResult>> {
//        return newsRepository.searchNews(
//            searchQuery = searchQuery,
//            sources = sources
//        )
//    }
}