package com.example.sigmaindustry.domain.usecases

import com.example.sigmaindustry.data.remote.dto.UserUpdate
import com.example.sigmaindustry.domain.repository.ServicesRepository
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    suspend operator fun invoke(userUpdate: UserUpdate) {
        return servicesRepository.updateUser(
            userUpdate = userUpdate
        )
    }
}