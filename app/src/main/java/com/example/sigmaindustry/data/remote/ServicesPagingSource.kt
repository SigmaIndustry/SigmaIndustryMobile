package com.example.sigmaindustry.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sigmaindustry.data.remote.dto.SearchRequest
import com.example.sigmaindustry.data.remote.dto.SearchResult

class ServicesPagingSource(
    private val servicesApi: ServicesApi,
) : PagingSource<Int, SearchResult>() {


    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalServicesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val page = params.key ?: 1
        return try {
            val searchResponse = servicesApi.getServices(SearchRequest())
            totalServicesCount += searchResponse.results.size
            val services = searchResponse.results.distinctBy { it.name } //Remove duplicates
            LoadResult.Page(
                data = services,
                nextKey = if (totalServicesCount == searchResponse.results.size) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}