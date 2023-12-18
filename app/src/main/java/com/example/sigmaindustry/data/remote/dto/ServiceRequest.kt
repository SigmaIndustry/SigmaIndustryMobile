package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ServiceRequest (
    @SerializedName("query")
    var searchQuery: String = "",

    @SerializedName("page_limit")
    var pageLimit: Int = 100,

)