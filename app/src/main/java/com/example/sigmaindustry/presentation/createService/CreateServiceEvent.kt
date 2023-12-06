package com.example.sigmaindustry.presentation.createService

import com.example.sigmaindustry.data.remote.dto.AddService

sealed class CreateServiceEvent {
    data class UpdateServiceRequest(val service: AddService) : CreateServiceEvent()

    object CreateService : CreateServiceEvent()
}