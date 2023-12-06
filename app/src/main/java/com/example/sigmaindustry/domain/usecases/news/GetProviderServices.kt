package com.example.sigmaindustry.domain.usecases.news

import com.example.sigmaindustry.data.remote.dto.ProviderServicesResponse
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class GetProviderServices @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(providerID: String): ProviderServicesResponse? {
        return servicesRepository.getServices(
            providerID = providerID,
        )
    }
}