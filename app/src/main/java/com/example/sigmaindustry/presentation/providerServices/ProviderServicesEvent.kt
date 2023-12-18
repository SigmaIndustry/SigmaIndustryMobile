package com.example.sigmaindustry.presentation.providerServices

sealed class ProviderServicesEvent {
    object GetServices : ProviderServicesEvent()
    data class DeleteService(val serviceId: Int): ProviderServicesEvent()
}