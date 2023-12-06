package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.AddService

class CreateServiceSource(
    private val servicesApi: ServicesApi
) {
    suspend fun createService(service: AddService) {
        try {
            servicesApi.createService(service)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
