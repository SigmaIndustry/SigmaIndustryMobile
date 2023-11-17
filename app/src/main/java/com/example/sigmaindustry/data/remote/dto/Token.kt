package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    var token: String,
)