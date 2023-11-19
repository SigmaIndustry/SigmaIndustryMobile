package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject
import com.example.sigmaindustry.data.remote.dto.RegisterProvider as RegisterProvider1

class RegisterProvider @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(provider: RegisterProvider1) {
        return servicesRepository.registerProvider(
            provider = provider
        )
    }
}