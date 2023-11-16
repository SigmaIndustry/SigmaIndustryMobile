package com.example.sigmaindustry.domain.usecases.news

import androidx.paging.PagingData
import com.example.sigmaindustry.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RateSenderService @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(token: String, serviceId: Int, rating: Float, feedback: String) {
        println(servicesRepository.sendRate(
            serviceId = serviceId,
            rating = rating,
            feedback = feedback,
            token = token
        ))
    }
}
class OrderSenderService @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(token: String, serviceId: Int, message: String) {
        println(servicesRepository.sendOrder(
            serviceId = serviceId,
            message = message,
            token = token
        ))
    }
}
