package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.Service
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {

    fun getServices(): Flow<PagingData<Service>>
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    fun searchServices(searchQuery: String): Flow<PagingData<Service>>



}