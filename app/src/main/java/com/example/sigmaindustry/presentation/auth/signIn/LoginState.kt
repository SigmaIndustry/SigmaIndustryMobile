package com.example.sigmaindustry.presentation.auth.signIn

import com.example.sigmaindustry.data.remote.dto.LoginRequest

data class LoginState (
    val loginRequest: LoginRequest = LoginRequest("", ""),
    val token: String = ""
)