package com.example.sigmaindustry.data.remote

import androidx.paging.PagingSource
import com.example.sigmaindustry.data.remote.dto.HistoryResponse

class HistorySource(
    private val servicesApi: ServicesApi,
) {
    suspend fun getHistory(email: String): HistoryResponse {
        try {
            return servicesApi.getHistory(email)
        } catch (e: Exception) {
            println("Error while load")
            e.printStackTrace()
            throw e
        }
    }
}
