package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.AddGeolocation
import com.example.sigmaindustry.data.remote.dto.AddService
import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.HistoryResponse
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.ProviderServicesResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {
    fun getServices(): Flow<PagingData<Service>>
   suspend fun getServices(providerID: String): ProviderServicesResponse?
   suspend fun createService(service: AddService)
    suspend fun addGeo(geo: AddGeolocation)
    suspend fun getHistory(email: String): HistoryResponse
    fun getCategories(): Map<String, String>
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun signUp(user: User): LoginResponse
    suspend fun updateUser(userUpdate: UserUpdate)
    suspend fun updateProvider(userUpdate: ProviderUpdate)
    suspend fun registerProvider(provider: RegisterProvider)
    suspend fun authenticate(token: Token): AuthenticateResponse
    fun searchServices(searchQuery: String): Flow<PagingData<Service>>
    suspend fun sendRate(token: String, serviceId: Int, rating: Float, feedback: String): Int
    suspend fun sendOrder(token: String, serviceId: Int, message: String)

}