package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.SearchRequest
import com.example.sigmaindustry.data.remote.dto.SearchResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicesApi {


    @POST("api/service/search")
    suspend fun getServices(@Body request: SearchRequest): SearchResponse




//    @POST("everything")
//    suspend fun searchNews(
//        @Query("q") searchQuery: String,
//        @Query("sources") sources: String,
//        @Query("page") page: Int,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): NewsResponse
}