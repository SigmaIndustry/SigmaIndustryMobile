package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddGeolocation(
    @SerializedName("token")
    val token: String,
    @SerializedName("service_id")
    val serviceID: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
