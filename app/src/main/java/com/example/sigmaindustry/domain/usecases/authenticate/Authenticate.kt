package com.example.sigmaindustry.domain.usecases.authenticate

import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class Authenticate @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(token: Token): AuthenticateResponse? {
        return try{
            servicesRepository.authenticate(token)
        } catch (e: Exception) {
            return null
        }
    }
}