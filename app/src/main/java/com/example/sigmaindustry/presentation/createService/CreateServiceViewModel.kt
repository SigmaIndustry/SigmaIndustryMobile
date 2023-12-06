package com.example.sigmaindustry.presentation.createService

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.AddService
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.domain.usecases.CreateServiceUseCase
import com.example.sigmaindustry.domain.usecases.authenticate.Authenticate
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CreateServiceViewModel @Inject constructor(
    private val createServiceUseCase: CreateServiceUseCase,
    private val readTokenUseCase: ReadTokenUseCase,
    private val authenticate: Authenticate
) : ViewModel() {

    private var _state = mutableStateOf(CreateServiceState())
    val state: State<CreateServiceState> = _state


     fun onEvent(event: CreateServiceEvent) {
        when (event) {
            is CreateServiceEvent.UpdateServiceRequest -> {
                _state.value = _state.value.copy(service  = event.service)
            }

            is CreateServiceEvent.CreateService -> {
                createService()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private  fun createService() {
        GlobalScope.launch {
            val token = async { readTokenUseCase() }
            val tokenFetched = token.await()
            if (tokenFetched != null) {
                val user =
                    async {
                        authenticate(
                            token = Token(tokenFetched)
                        )
                    }
                if(state.value.service?.name == null){
                }
                 createServiceUseCase(
                    AddService(user.await().provider.providerID.toInt(),
                        state.value.service!!.name,
                        state.value.service!!.pictures,
                        state.value.service!!.price,
                        state.value.service!!.category,
                        state.value.service!!.description)
                )
            }
        }
    }
}