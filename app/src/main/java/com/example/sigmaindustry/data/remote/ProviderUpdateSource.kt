package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.ProviderUpdate

class ProviderUpdateSource(
    private val servicesApi: ServicesApi,
)  {



    suspend fun updateProvider(provider: ProviderUpdate) {
        try {
            servicesApi.updateProvider(provider)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}