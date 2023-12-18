package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName


data class SearchRequest (
    @SerializedName("query")
    var searchQuery: String = "",

    @SerializedName("page_limit")
    var pageLimit: Int = 100,

    @SerializedName("page_offset")
    var pageOffset: Int = 0,

    @SerializedName("min_price")
    var minPrice: Int = 0,

    @SerializedName("max_price")
    var maxPrice: Int = 10000000,

    @SerializedName("category")
    var category: String = "00",

    @SerializedName("min_rating")
    var minRating: Int = 0,

    @SerializedName("has_reviews")
    var hasReviews: Boolean = false
)