package com.example.sigmaindustry.domain.usecases.login

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse? {
        return try {
            servicesRepository.login(
                loginRequest = loginRequest
            )
        } catch (e: Exception) {
            return null
        }
    }
}