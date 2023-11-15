package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.ServiceRequest
import com.example.sigmaindustry.data.remote.dto.ServiceResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicesApi {

    @POST("api/service/search")
    suspend fun getServices(@Body request: ServiceRequest): ServiceResponse



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