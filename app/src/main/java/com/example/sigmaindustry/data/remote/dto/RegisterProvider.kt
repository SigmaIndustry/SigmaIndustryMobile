package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterProvider(
    @SerializedName("email")
    var email: String = "",

    @SerializedName("business_name")
    var businessName: String = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("phone_number")
    var phoneNumber: String = "",

    @SerializedName("city")
    var city: String = "",

    @SerializedName("work_time")
    var workTime: String = "",

    )
