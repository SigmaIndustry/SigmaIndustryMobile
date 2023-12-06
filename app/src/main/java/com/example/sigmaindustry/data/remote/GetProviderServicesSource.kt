package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.ProviderServicesResponse
import com.example.sigmaindustry.data.remote.dto.ServiceResponse

class GetProviderServicesSource(
    private val servicesApi: ServicesApi,
)  {

    suspend fun getServices(providerId: String): ProviderServicesResponse? {
        return try {
            servicesApi.getProviderServices(providerId)
        } catch (e: Exception) {
            println("Error while load")
            e.printStackTrace()
            throw e
        }
    }

}