package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserUpdate(
    @SerializedName("token")
    var token: String,
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("birth_date")
    var birthDate: String,

)
