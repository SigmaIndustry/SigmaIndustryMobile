package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.User

class SignUpSource(
    private val servicesApi: ServicesApi,
)  {



    suspend fun signUp(user: User): LoginResponse {
        return try {
            servicesApi.signUp(user)
        } catch (e: Exception) {
            e.printStackTrace()
            LoginResponse(400, "null")
        }
    }
}