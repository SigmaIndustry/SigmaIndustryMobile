package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.data.remote.dto.AddService
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class CreateServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(service: AddService) {
        return servicesRepository.createService(
            service = service
        )
    }
}