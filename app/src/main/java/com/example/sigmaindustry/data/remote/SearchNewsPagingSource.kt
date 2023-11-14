package com.example.sigmaindustry.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sigmaindustry.data.remote.dto.SearchResult


//class SearchNewsPagingSource(
//    private val api: ServicesApi,
//    private val searchQuery: String,
//    private val sources: String
//) : PagingSource<Int, SearchResult>() {
//
//    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
//        return state.anchorPosition?.let { anchorPage ->
//            val page = state.closestPageToPosition(anchorPage)
//            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
//        }
//    }
//
//    private var totalNewsCount = 0

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
//        val page = params.key ?: 1
//        return try {
//            val newsResponse = api.searchNews(searchQuery = searchQuery, sources = sources, page = page)
//            totalNewsCount += newsResponse.articles.size
//           // val articles = newsResponse.articles.distinctBy { it.title } //Remove duplicates
//
//            //LoadResult.Page(
//              //  data = articles,
//             //   nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
//           //     prevKey = null
//          //  )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            LoadResult.Error(throwable = e)
//        }
//    }
//
//
//}