package com.example.sigmaindustry.domain.repository

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.ServiceResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getServices(): Flow<PagingData<ServiceResponse>>

    fun login(loginRequest: LoginRequest): LoginResponse

    fun searchNews(searchQuery: String): Flow<PagingData<ServiceResponse>>

    fun getProvider(providerId: Int): ProviderResponse
}