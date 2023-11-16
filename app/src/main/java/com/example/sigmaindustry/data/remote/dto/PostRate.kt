package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PostRateRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("feedback")
    val feedback: String
)

data class PostRateResponse(
    @SerializedName("rating_id")
    val ratingId: Int
)

data class PostOrderRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("message")
    val message: String
)
