package com.example.sigmaindustry.domain.usecases.news

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServices @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    operator fun invoke(): Flow<PagingData<Service>> {
        return servicesRepository.getServices()
    }
}