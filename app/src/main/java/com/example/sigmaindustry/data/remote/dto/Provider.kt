package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProviderRequest (
    @SerializedName("provider_id")
    val providerId: Int = 0
)

data class ProviderResponse (
    @SerializedName("business_name")
    val businessName: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("phone_number")
    val phoneNumber: String = "",

    @SerializedName("city")
    val city: String = "",

    @SerializedName("work_time")
    val workTime: String = "",

    @SerializedName("created_at")
    val createdAt: String = "",
)
