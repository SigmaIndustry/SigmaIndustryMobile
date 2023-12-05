package com.example.sigmaindustry.presentation.auth.profile

import androidx.paging.PagingData
import com.example.sigmaindustry.data.remote.dto.AuthenticateResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import kotlinx.coroutines.flow.Flow

data class ProfileScreenState(
    var token: String? = null,
    val authenticateResponse: AuthenticateResponse? = null,
    val update: UserUpdate? = null,
    val updateProvider: ProviderUpdate? = null,
    val providerServiceList: Flow<PagingData<Service>>? = null
)

