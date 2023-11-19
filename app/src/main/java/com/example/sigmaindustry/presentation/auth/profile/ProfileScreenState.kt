package com.example.sigmaindustry.presentation.auth.profile

import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse

data class ProfileScreenState (
    var token: String? = null,
    val authenticateResponse: AuthenticateResponse? = null
)

