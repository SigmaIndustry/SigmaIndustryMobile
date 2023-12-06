package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse

class LoginSource(
    private val servicesApi: ServicesApi,
) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        try {
            return servicesApi.login(loginRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}