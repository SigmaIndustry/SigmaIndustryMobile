package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.AddGeolocation

class AddGeoSource (
    private val servicesApi: ServicesApi
    ) {
        suspend fun addGeo(geo: AddGeolocation) {
            try {
                servicesApi.addGeo(geo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }