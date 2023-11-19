package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.RegisterProvider

class RegisterProviderSource(
    private val servicesApi: ServicesApi,
)  {



    suspend fun registerProvider(provider: RegisterProvider) {
        try {
            servicesApi.registerProvider(provider)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}