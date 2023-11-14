package com.example.sigmaindustry.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sigmaindustry.data.remote.dto.SearchRequest
import com.example.sigmaindustry.data.remote.dto.SearchResponse
import com.example.sigmaindustry.data.remote.dto.SearchResult


class SearchNewsPagingSource(
    private val api: ServicesApi,
    private val searchQuery: String,
) : PagingSource<Int, SearchResult>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.getServices(SearchRequest(searchQuery))
            totalNewsCount += newsResponse.results.size
            val services = newsResponse.results.distinctBy { it.id }
            LoadResult.Page(
                data = services,
                nextKey = if (totalNewsCount == newsResponse.size) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }


}