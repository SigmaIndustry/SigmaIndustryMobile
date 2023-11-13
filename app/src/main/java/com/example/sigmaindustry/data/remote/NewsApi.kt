package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.NewsResponse
import com.example.sigmaindustry.util.Constants.API_KEY
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsApi {

    @POST("api/service/search")
    suspend fun getServices(@Body request: ): NewsResponse

    @POST("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}