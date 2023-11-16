package com.example.sigmaindustry.presentation.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.signUp.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUp
) : ViewModel() {

    private var _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state


    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UpdateSignUpRequest -> {
                _state.value = _state.value.copy(user = event.user)
            }

            is SignUpEvent.SignUp -> {
                signUp()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun signUp() {

        GlobalScope.launch {
            val signUpResult = signUp(
                user = _state.value.user,
            )
            _state.value = _state.value.copy(loginResponse = signUpResult)
        }
    }


}