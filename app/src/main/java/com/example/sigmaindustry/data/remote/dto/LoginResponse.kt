package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var statusCode: Int,

    @SerializedName("token")
    var token: String,
)