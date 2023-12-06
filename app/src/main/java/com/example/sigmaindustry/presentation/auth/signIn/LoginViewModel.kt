package com.example.sigmaindustry.presentation.auth.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.login.Login
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import com.example.sigmaindustry.domain.usecases.token.SaveToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login,
    private val saveToken: SaveToken,
    private val readTokenUseCase: ReadTokenUseCase
) : ViewModel() {

    private var _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    var errorHandler: (String) -> Unit = {}

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
            val loginResult = login(loginRequest = _state.value.loginRequest)
            if (loginResult == null) {
                errorHandler("Error when login")
                return@launch
            }
            saveToken(loginResult.token)
            readTokenUseCase()?.let {
                _state.value = _state.value.copy(token = it)
            }
        }
    }
}