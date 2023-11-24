package com.example.sigmaindustry.presentation.auth.profile

import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.UserUpdate

data class ProfileScreenState(
    var token: String? = null,
    val authenticateResponse: AuthenticateResponse? = null,
    val update: UserUpdate? = null,
    val updateProvider: ProviderUpdate? = null
)

