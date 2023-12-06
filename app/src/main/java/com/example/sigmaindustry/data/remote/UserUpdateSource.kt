package com.example.sigmaindustry.data.remote

import com.example.sigmaindustry.data.remote.dto.UserUpdate

class UserUpdateSource (
    private val servicesApi: ServicesApi,
)  {
    suspend fun updateUser(user: UserUpdate) {
         try {
            servicesApi.updateUser(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}