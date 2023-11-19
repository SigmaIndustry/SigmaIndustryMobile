package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(
    @SerializedName("user")
    val user: User,

    @SerializedName("service_provider")
    val provider: ProviderResponse
)
