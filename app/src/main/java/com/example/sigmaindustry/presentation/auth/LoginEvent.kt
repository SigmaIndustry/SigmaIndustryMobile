package com.example.sigmaindustry.presentation.auth

import com.example.sigmaindustry.data.remote.dto.LoginRequest

sealed class LoginEvent {

    data class UpdateLoginRequest(val loginRequest: LoginRequest) : LoginEvent()

    object Login : LoginEvent()
}