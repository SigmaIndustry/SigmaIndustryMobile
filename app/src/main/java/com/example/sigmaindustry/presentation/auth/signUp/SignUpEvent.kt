package com.example.sigmaindustry.presentation.auth.signUp

import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.RegisterProvider as Provider

sealed class SignUpEvent {

    data class UpdateSignUpRequest(val user: User) : SignUpEvent()
    data class UpdateProvider(val provider: Provider) : SignUpEvent()
    object SignUp : SignUpEvent()
    object RegisterProvider : SignUpEvent()
}