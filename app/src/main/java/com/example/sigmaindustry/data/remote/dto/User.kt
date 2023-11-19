package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class User(
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

    @SerializedName("sex")
    val sex: String,

    @SerializedName("profile_picture")
    var photoUrl: String,

    @SerializedName("role")
    var role: String,
)

enum class Sex {
    Male, Female
}

enum class Role{
    User,ServiceProvider
}
