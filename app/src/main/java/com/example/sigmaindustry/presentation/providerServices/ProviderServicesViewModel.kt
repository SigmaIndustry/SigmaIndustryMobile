package com.example.sigmaindustry.presentation.providerServices

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.news.GetProviderServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProviderServicesViewModel @Inject constructor(
    private val getProviderServices: GetProviderServices,
    private val serviceRepository: ServicesRepository
) : ViewModel() {

    private var _state = mutableStateOf(ProviderServicesState())
    val state: State<ProviderServicesState> = _state


    fun changeServiceCategory(s: Service): Service {
        return s.copy(category = serviceRepository.getCategories()[s.category] ?: "Unknown")
    }

    fun onEvent(event: ProviderServicesEvent) {
        when (event) {

            is ProviderServicesEvent.GetServices -> {
                getServices()
            }
        }
    }

    private fun getServices() {
        GlobalScope.launch {
            val services = getProviderServices(
                providerID = "1",
            )
            _state.value = _state.value.copy(services = services)
        }
    }
}