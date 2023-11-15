package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.ProviderRequest
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.SearchResponse
import com.example.sigmaindustry.data.remote.dto.ServiceRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicesApi {

    @POST("api/service/search")
    suspend fun getServices(@Body request: ServiceRequest): SearchResponse


    @POST
    suspend fun getProvider(@Body request: ProviderRequest): ProviderResponse

    @POST("security/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

//    @POST("everything")
//    suspend fun searchNews(
//        @Query("q") searchQuery: String,
//        @Query("sources") sources: String,
//        @Query("page") page: Int,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): NewsResponse
}