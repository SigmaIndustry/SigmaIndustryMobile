package com.example.sigmaindustry.presentation.auth.signUp

import com.example.sigmaindustry.data.remote.dto.LoginResponse
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.User

data class SignUpState (
    var user: User = User("", "","","","","","",""),
    var provider: RegisterProvider = RegisterProvider("", "", "", "", "", ""),
    val loginResponse: LoginResponse = LoginResponse(400,""),
    val token: String = "",
)