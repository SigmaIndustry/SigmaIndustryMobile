package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterProvider(
    @SerializedName("email")
    val email: String = "",

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

)
