package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProviderUpdate(
    @SerializedName("token")
    var token: String,

    @SerializedName("first_name")
    var firstName: String?,

    @SerializedName("last_name")
    var lastName: String?,

    @SerializedName("birth_date")
    var birthDate: String?,

    @SerializedName("business_name")
    var businessName: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("phone_number")
    var phoneNumber: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("work_time")
    var workTime: String? = null
)
