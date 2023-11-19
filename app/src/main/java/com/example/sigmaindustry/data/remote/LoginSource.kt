package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse

class LoginSource(
    private val servicesApi: ServicesApi,
) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return try {
            //loginRequest.email = "sigma@nure.ua"
            //loginRequest.password = "hello.world_123"
            println(loginRequest)
            servicesApi.login(loginRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            LoginResponse(400, "null")
        }
    }
}