package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.NewsResponse
import com.loc.newsapp.util.Constants.API_KEY
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsApi {

    @POST("everything")
    suspend fun getNews(
    ): NewsResponse

    @POST("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}