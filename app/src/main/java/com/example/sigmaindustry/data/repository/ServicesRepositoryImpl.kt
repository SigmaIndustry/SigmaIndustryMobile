package com.example.sigmaindustry.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.AuthenticateSource
import com.example.sigmaindustry.data.remote.CreateServiceSource
import com.example.sigmaindustry.data.remote.GetProviderServicesSource
import com.example.sigmaindustry.data.remote.HistorySource
import com.example.sigmaindustry.data.remote.LoginSource
import com.example.sigmaindustry.data.remote.ProviderUpdateSource
import com.example.sigmaindustry.data.remote.RegisterProviderSource
import com.example.sigmaindustry.data.remote.SearchServicesPagingSource
import com.example.sigmaindustry.data.remote.ServicesApi
import com.example.sigmaindustry.data.remote.ServicesPagingSource
import com.example.sigmaindustry.data.remote.SignUpSource
import com.example.sigmaindustry.data.remote.UserUpdateSource
import com.example.sigmaindustry.data.remote.dto.AddService
import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.HistoryResponse
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.PostOrderRequest
import com.example.sigmaindustry.data.remote.dto.PostRateRequest
import com.example.sigmaindustry.data.remote.dto.ProviderServicesResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import com.example.sigmaindustry.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(
    private val servicesApi: ServicesApi,
) : ServicesRepository {

    override fun getServices(): Flow<PagingData<Service>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ServicesPagingSource(servicesApi = servicesApi)
            }
        ).flow
    }

    override suspend fun getHistory(email: String): HistoryResponse {
        return HistorySource(servicesApi).getHistory(email)
    }

    override fun getCategories(): Map<String, String> {
        return mapOf("00" to "Food & Drinks", "01" to "Car service")
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return LoginSource(servicesApi).login(loginRequest)
    }
    override suspend fun signUp(user: User): LoginResponse {
        return SignUpSource(servicesApi).signUp(user)
    }

    override suspend fun authenticate(token: Token): AuthenticateResponse {
        return AuthenticateSource(servicesApi).authenticate(token)
    }

    override suspend fun updateUser(userUpdate: UserUpdate) {
        UserUpdateSource(servicesApi).updateUser(userUpdate)
    }

    override suspend fun updateProvider(providerUpdate: ProviderUpdate) {
        ProviderUpdateSource(servicesApi).updateProvider(providerUpdate)
    }
    override suspend fun registerProvider(provider: RegisterProvider) {
        RegisterProviderSource(servicesApi).registerProvider(provider)
    }
    override fun searchServices(
        searchQuery: String,
    ): Flow<PagingData<Service>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchServicesPagingSource(
                    api = servicesApi,
                    searchQuery = searchQuery,
                )
            }
        ).flow
    }

    override suspend fun getServices(providerID: String): ProviderServicesResponse? {

            return GetProviderServicesSource(servicesApi).getServices(providerID)
    }

    override suspend fun createService(service: AddService) {
        CreateServiceSource(servicesApi).createService(service)
    }
    override suspend fun sendRate(token: String, serviceId: Int, rating: Float, feedback: String): Int {
        val resp = servicesApi.postRate(PostRateRequest(token, serviceId, rating, feedback))
        return resp.ratingId
    }

    override suspend fun sendOrder(token: String, serviceId: Int, message: String) {
        servicesApi.postOrder(PostOrderRequest(token, serviceId, message = message))
    }

}
