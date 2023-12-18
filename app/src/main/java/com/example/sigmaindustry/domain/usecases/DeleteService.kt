package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import javax.inject.Inject

class DeleteService @Inject constructor(
    val servicesRepository: ServicesRepository,
    val tokenUseCase: ReadTokenUseCase
) {
     suspend operator fun invoke(serviceId: Int): Boolean {
         val tokens = tokenUseCase() ?: return false
         return servicesRepository.deleteService(tokens, serviceId)
     }

}