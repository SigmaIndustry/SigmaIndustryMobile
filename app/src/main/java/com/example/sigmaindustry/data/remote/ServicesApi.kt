package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.PostOrderRequest
import com.example.sigmaindustry.data.remote.dto.PostRateRequest
import com.example.sigmaindustry.data.remote.dto.PostRateResponse
import com.example.sigmaindustry.data.remote.dto.ProviderRequest
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.ServiceRequest
import com.example.sigmaindustry.data.remote.dto.ServiceResponse
import com.example.sigmaindustry.data.remote.dto.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicesApi {

    @POST("api/service/search")
    suspend fun getServices(@Body request: ServiceRequest): ServiceResponse

    @POST("security/register")
    suspend fun signUp(@Body request: User): LoginResponse
    @POST("")
    suspend fun getProvider(@Body request: ProviderRequest): ProviderResponse

    @POST("/api/service/rate")
    suspend fun postRate(@Body request: PostRateRequest): PostRateResponse

    @POST("/api/service/order")
    suspend fun postOrder(@Body request: PostOrderRequest)

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