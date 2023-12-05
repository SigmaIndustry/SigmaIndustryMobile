package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.Token
import com.example.sigmaindustry.domain.usecases.UpdateProvider
import com.example.sigmaindustry.domain.usecases.UpdateUser
import com.example.sigmaindustry.domain.usecases.authenticate.Authenticate
import com.example.sigmaindustry.domain.usecases.news.GetProviderServices
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val readTokenUseCase: ReadToken,
    private val authenticateUseCase: Authenticate,
    private val updateUser: UpdateUser,
    private val updateProviderServices: GetProviderServices,
    private val updateProvider: UpdateProvider
) : ViewModel(), DefaultLifecycleObserver {

    private var _state = mutableStateOf(ProfileScreenState())
    val state: State<ProfileScreenState> = _state


    suspend fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.UpdateToken -> {
                _state.value = _state.value.copy(token = event.token)
            }
            is ProfileScreenEvent.UpdateUser -> {
                _state.value = _state.value.copy(update = event.user)
            }
            is ProfileScreenEvent.UpdateProvider -> {
                _state.value = _state.value.copy(updateProvider = event.provider)
            }
            is ProfileScreenEvent.UpdateProviderServices ->{
                //_state.value = _state.value.copy(providerServiceList = event.services)
            }
            is ProfileScreenEvent.ProfileScreen -> {
                authenticate()
            }
            is ProfileScreenEvent.Authenticate -> {
                authenticate()
            }
            is ProfileScreenEvent.UpdateProviderServicesEvent -> {
                updateProviderServices()
            }
            is ProfileScreenEvent.UpdateProviderEvent -> {
                updateProvider()
            }
            is ProfileScreenEvent.Update -> updateUser()
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun authenticate() {
        GlobalScope.launch {
            val token = async { readTokenUseCase() }
            _state.value = _state.value.copy(token = token.await())
            val tokenFetched = token.await()
            if (tokenFetched != null) {
                val user =
                    async {
                        authenticateUseCase(
                            token = Token(tokenFetched)
                        )
                    }
                _state.value = _state.value.copy(authenticateResponse = user.await())
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun updateUser() {
        GlobalScope.launch {
            updateUser(state.value.update!!)
        }
    }


     private fun updateProviderServices() {
        GlobalScope.launch {
            updateProviderServices(state.value.authenticateResponse?.provider?.providerID!!)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun updateProvider() {
        GlobalScope.launch {
            updateProvider(state.value.updateProvider!!)
        }
    }
}