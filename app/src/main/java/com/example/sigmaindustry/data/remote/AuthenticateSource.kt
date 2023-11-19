package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.Token

class AuthenticateSource(
    private val servicesApi: ServicesApi,
) {

    suspend fun authenticate(token: Token): AuthenticateResponse {
        return try {
            servicesApi.authenticate(token)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}