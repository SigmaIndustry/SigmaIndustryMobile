package com.example.sigmaindustry.domain.usecases.signUp

import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(user: User): LoginResponse? {
        return try {
            servicesRepository.signUp(
                user = user
            )
        } catch (e: Exception) {
            null
        }
    }
}