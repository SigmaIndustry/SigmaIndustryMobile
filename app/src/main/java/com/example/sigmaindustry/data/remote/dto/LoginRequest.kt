package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String = "sigma@nure.ua",

    @SerializedName("password")
    var password: String = "hello.world_123"
)