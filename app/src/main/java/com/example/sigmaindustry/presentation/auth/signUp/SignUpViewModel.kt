package com.example.sigmaindustry.presentation.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.domain.usecases.signUp.SignUp
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import com.example.sigmaindustry.domain.usecases.token.SaveToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.sigmaindustry.domain.usecases.RegisterProvider as RegisterProviderUS

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUp,
    private val registerProvider: RegisterProviderUS,
    private val saveToken: SaveToken,
    private val readTokenUseCase: ReadTokenUseCase
) : ViewModel() {

    private var _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state
    var errorHandler: (String) -> Unit = {}

    fun updateUser(newUser: User) {
        _state.value = _state.value.copy(user = newUser)
    }

    fun updateProvider(newProvider: RegisterProvider) {
        _state.value = _state.value.copy(provider = newProvider)
    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UpdateSignUpRequest -> {
                _state.value = _state.value.copy(user = event.user)
            }
            is SignUpEvent.UpdateProvider -> {
                _state.value = _state.value.copy(provider = event.provider)
            }
            is SignUpEvent.SignUp -> {
                signUp()
                _state.value = SignUpState()
            }
            is SignUpEvent.RegisterProvider -> {
                registerProvider()
                _state.value = SignUpState()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun signUp() {
        GlobalScope.launch {
            val signUpResult = signUp(user = _state.value.user)
            if (signUpResult == null) {
                errorHandler("Error")
                return@launch
            }
            saveToken(signUpResult.token)
            readTokenUseCase()?.let{
                _state.value = _state.value.copy(token = it)
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun registerProvider() {
        GlobalScope.launch {
             registerProvider(
                provider = _state.value.provider,
            )
        }
    }


}