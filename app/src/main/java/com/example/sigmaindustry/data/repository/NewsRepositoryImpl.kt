package com.example.sigmaindustry.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import com.example.sigmaindustry.data.remote.SearchNewsPagingSource

import com.example.sigmaindustry.data.remote.ServicesApi
import com.example.sigmaindustry.data.remote.ServicesPagingSource
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.ProviderRequest
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val servicesApi: ServicesApi,
) : NewsRepository {

    override fun getServices(): Flow<PagingData<SearchResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ServicesPagingSource(servicesApi = servicesApi)
            }
        ).flow
    }


        override fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
         return login(loginRequest)
    }


    override fun searchNews(
        searchQuery: String,
    ): Flow<PagingData<SearchResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = servicesApi,
                    searchQuery = searchQuery,
                )
            }
        ).flow
    }

    override fun getProvider(providerId: Int): ProviderResponse {
        val result = servicesApi.getProvider(ProviderRequest(providerId))
        return ProviderResponse()
    }
}
