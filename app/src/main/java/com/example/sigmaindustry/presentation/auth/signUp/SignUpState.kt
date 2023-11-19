package com.example.sigmaindustry.presentation.auth.signUp

import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.User

data class SignUpState (
    val user: User = User("", "","","","","","",""),
    val loginResponse: LoginResponse = LoginResponse(400,""),
    val token: String = "",
    val provider: RegisterProvider = RegisterProvider()
)