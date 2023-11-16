package com.example.sigmaindustry.presentation.auth.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.login.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login
) : ViewModel() {

    private var _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateLoginRequest -> {
                _state.value = _state.value.copy(loginRequest = event.loginRequest)
            }

            is LoginEvent.Login -> {
                login()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun login() {

        GlobalScope.launch {
             var loginResult = login(
                loginRequest = _state.value.loginRequest,
            )
            _state.value = _state.value.copy(loginResponse = loginResult)
        }
    }


}