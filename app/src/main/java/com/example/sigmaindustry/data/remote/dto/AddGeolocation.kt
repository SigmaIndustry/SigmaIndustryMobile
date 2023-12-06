package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddGeolocation(
    @SerializedName("token")
    val token:String,
    @SerializedName("latitude")
    val serviceID:Int,
    @SerializedName("latitude")
    val latitude:Int,
    @SerializedName("longitude")
    val longitude:Int
)
