package com.example.sigmaindustry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddService(
@SerializedName("provider_id")
val providerID: Int,

@SerializedName("name")
val name: String,

@SerializedName("pictures")
val pictures: List<String>,

@SerializedName("price")
val price: Int,

@SerializedName("category")
val category: String,

@SerializedName("description")
val description: String
)
