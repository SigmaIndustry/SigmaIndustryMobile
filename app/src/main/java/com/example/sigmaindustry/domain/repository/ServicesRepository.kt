package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.EntriesResponse
import com.example.sigmaindustry.data.remote.dto.HistoryResponse
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {
    fun getServices(): Flow<PagingData<Service>>
    suspend fun getHistory(email: String): HistoryResponse
    fun getCategories(): Map<String, String>
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun signUp(user: User): LoginResponse
    suspend fun updateUser(userUpdate: UserUpdate)
    suspend fun registerProvider(provider: RegisterProvider)
    suspend fun authenticate(token: Token): AuthenticateResponse
    fun searchServices(searchQuery: String): Flow<PagingData<Service>>
    suspend fun sendRate(token: String, serviceId: Int, rating: Float, feedback: String): Int
    suspend fun sendOrder(token: String, serviceId: Int, message: String)
}