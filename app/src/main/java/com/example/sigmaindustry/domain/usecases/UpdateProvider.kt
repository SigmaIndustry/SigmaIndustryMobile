package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class UpdateProvider  @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(provider: ProviderUpdate) {
        return servicesRepository.updateProvider(
            provider
        )
    }
}