package com.example.sigmaindustry.data.remote.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceResponse (
    @SerializedName("code")
    var statusCode: Int,

    @SerializedName("size")
    var size: Int = 10,

    @SerializedName("results")
    var results: List<Service>
): Parcelable

@Parcelize
data class Service(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("pictures")
    var pictures: List<String>,

    @SerializedName("geolocation")
    val geolocation: GeoRespParc?,

    @SerializedName("description")
    var description: String,

    @SerializedName("price")
    var price: Double,

    @SerializedName("category")
    var category: String,

    @SerializedName("rating")
    var rating: Double,

    @SerializedName("provider")
    var provider: Int

): Parcelable
@Parcelize
data class GeoRespParc (
    val id: Int,
    val country: String,
    val region: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
) :Parcelable