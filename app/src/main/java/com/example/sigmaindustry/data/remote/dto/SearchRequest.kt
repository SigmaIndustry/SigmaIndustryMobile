package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchRequest (
    @SerializedName("query")
    var searchQuery: String,

    @SerializedName("page_limit")
    var pageLimit: Int,

    @SerializedName("page_offset")
    var pageOffset: Int,

    @SerializedName("min_price")
    var minPrice: Int,

    @SerializedName("max_price")
    var maxPrice: Int,

    @SerializedName("category")
    var category: String,

    @SerializedName("min_rating")
    var minRating: Int,

    @SerializedName("has_reviews")
    var hasReviews: Boolean
)