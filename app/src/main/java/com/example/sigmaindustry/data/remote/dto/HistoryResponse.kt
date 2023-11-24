package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName


data class HistoryResponse (
   val entries: List<EntriesResponse>
)

data class EntriesResponse (
    val email: String,
    val message: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("service")
    val serviceId: Int
)