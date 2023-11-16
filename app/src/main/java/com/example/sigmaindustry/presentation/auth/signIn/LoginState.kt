package com.example.sigmaindustry.presentation.auth.signIn

import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.data.remote.dto.LoginResponse

data class LoginState (
    val loginRequest: LoginRequest = LoginRequest("", ""),
    val loginResponse: LoginResponse = LoginResponse(400,"")
)