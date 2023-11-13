package com.example.sigmaindustry.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("code")
    var statusCode: Int,

    @SerializedName("size")
    var size: Int,

    @SerializedName("results")
    var results: List<SearchResult>
)


data class SearchResult(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("pictures")
    var pictures: List<String>,

    @SerializedName("description")
    var description: String,

    @SerializedName("price")
    var price: Int,

    @SerializedName("category")
    var category: String,

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("provider")
    var provider: Int

)