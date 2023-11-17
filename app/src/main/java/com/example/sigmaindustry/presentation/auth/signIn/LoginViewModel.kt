package com.example.sigmaindustry.presentation.auth.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.login.Login
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import com.example.sigmaindustry.domain.usecases.token.SaveToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login,
    private val saveToken: SaveToken,
    private val readToken: ReadToken
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
             val loginResult = login(
                loginRequest = _state.value.loginRequest,
            )
            saveToken(loginResult.token)
            println("Token is: ${loginResult.token}")
            readToken()?.let{
                 _state.value = _state.value.copy(token = it)
            }
        }
    }


}