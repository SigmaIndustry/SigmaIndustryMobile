package com.example.sigmaindustry.presentation.providerServices

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.authenticate.Authenticate
import com.example.sigmaindustry.domain.usecases.news.GetProviderServices
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProviderServicesViewModel @Inject constructor(
    private val getProviderServices: GetProviderServices,
    private val readTokenUseCase: ReadTokenUseCase,
    private val authenticateUseCase: Authenticate,
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
            val token = async { readTokenUseCase() }
            val tokenFetched = token.await()
            if (tokenFetched != null) {
                val user =
                    async {
                        authenticateUseCase(
                            token = Token(tokenFetched)
                        )
                    }
                val providerID = user.await().provider.providerID
                val services = getProviderServices(
                    providerID = providerID,
                )
                _state.value = _state.value.copy(services = services)
            }
        }
    }
}