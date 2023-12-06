package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.data.remote.dto.AddGeolocation
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class AddGeoUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(geo: AddGeolocation) {
        return servicesRepository.addGeo(
            geo = geo
        )
    }
}