package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.HistoryResponse
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.PostOrderRequest
import com.example.sigmaindustry.data.remote.dto.PostRateRequest
import com.example.sigmaindustry.data.remote.dto.PostRateResponse
import com.example.sigmaindustry.data.remote.dto.ProviderRequest
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.ServiceRequest
import com.example.sigmaindustry.data.remote.dto.ServiceResponse
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServicesApi {

    @POST("api/service/search")
    suspend fun getServices(@Body request: ServiceRequest): ServiceResponse

    @POST("security/register")
    suspend fun signUp(@Body request: User): LoginResponse
    @POST("")
    suspend fun getProvider(@Body request: ProviderRequest): ProviderResponse
    @POST("security/register_provider")
    suspend fun registerProvider(@Body request: RegisterProvider)
    @POST("security/update")
    suspend fun updateUser(@Body request: UserUpdate)

    @POST("security/update")
    suspend fun updateProvider(@Body request: ProviderUpdate)
    @POST("security/authenticate")
    suspend fun authenticate(@Body request: Token): AuthenticateResponse

    @POST("/api/service/rate")
    suspend fun postRate(@Body request: PostRateRequest): PostRateResponse

    @POST("/api/service/order")
    suspend fun postOrder(@Body request: PostOrderRequest)

    @POST("security/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/get_history/{email}")
    suspend fun getHistory(@Path("email") email: String): HistoryResponse

    @GET("api/service/list/@{provider_id}")
    suspend fun getProviderServices(@Path("provider_id") providerId: String): ServiceResponse

//    @POST("everything")
//    suspend fun searchNews(
//        @Query("q") searchQuery: String,
//        @Query("sources") sources: String,
//        @Query("page") page: Int,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): NewsResponse
}