package com.example.sigmaindustry.presentation.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.domain.usecases.RegisterProvider as RegisterProviderUS
import com.example.sigmaindustry.domain.usecases.signUp.SignUp
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import com.example.sigmaindustry.domain.usecases.token.SaveToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUp,
    private val registerProvider: RegisterProviderUS,
    private val saveToken: SaveToken,
    private val readToken: ReadToken
) : ViewModel() {

    private var _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

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
            }
            is SignUpEvent.RegisterProvider -> {
                registerProvider()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun signUp() {

        GlobalScope.launch {
            val signUpResult = signUp(
                user = _state.value.user,
            )
            saveToken(signUpResult.token)
            println("Token is: ${signUpResult.token}")
            readToken()?.let{
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