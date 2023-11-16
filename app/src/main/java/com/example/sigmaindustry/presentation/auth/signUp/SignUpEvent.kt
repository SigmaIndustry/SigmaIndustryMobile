package com.example.sigmaindustry.presentation.auth.signUp

import com.example.sigmaindustry.data.remote.dto.User

sealed class SignUpEvent {

    data class UpdateSignUpRequest(val user: User) : SignUpEvent()

    object SignUp : SignUpEvent()
}