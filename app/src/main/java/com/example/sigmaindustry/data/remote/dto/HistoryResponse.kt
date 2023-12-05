package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName


data class HistoryResponse (
   val entries: List<EntriesResponse>
)

data class EntriesResponse (
    val id: Int,
    val service: InnerServiceResponse,
    val email: String,
    val message: String,
    @SerializedName("created_at")
    val createdAt: String,
)

data class InnerServiceResponse (
    val id: Int,
    val pictures: List<String>,
    val rating: Float,
    val reviews: List<String>?,
    val geolocation: GeoResp,
    val name: String,
    val description: String,
    val price: Float?,
    val category: String,
    @SerializedName("created_at")
    val createdAt: String,
    val provider: Int
)

data class GeoResp (
    val id: Int,
    val country: String,
    val region: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
)