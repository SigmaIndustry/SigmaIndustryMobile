package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ServiceRequest (
    @SerializedName("query")
    var searchQuery: String = "",

    @SerializedName("page_limit")
    var pageLimit: Int = 100,

)

data class ServerDeleteReq (
    @SerializedName("service_id")
    val serviceId: Int = 0,
    @SerializedName("token")
    val token: String = ""
)