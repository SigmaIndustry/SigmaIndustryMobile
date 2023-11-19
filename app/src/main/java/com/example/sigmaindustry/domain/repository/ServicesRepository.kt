package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.data.remote.dto.User
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {
    fun getServices(): Flow<PagingData<Service>>
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun signUp(user: User): LoginResponse
    suspend fun authenticate(token: Token): AuthenticateResponse
    fun searchServices(searchQuery: String): Flow<PagingData<Service>>
    suspend fun sendRate(token: String, serviceId: Int, rating: Float, feedback: String): Int
    suspend fun sendOrder(token: String, serviceId: Int, message: String)
}