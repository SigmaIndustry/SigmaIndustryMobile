package com.example.sigmaindustry.presentation.auth.profile

import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.UserUpdate

sealed class ProfileScreenEvent{
    data class UpdateToken(val token: String) : ProfileScreenEvent()
    data class UpdateUser(val user: UserUpdate) : ProfileScreenEvent()
    data class UpdateProvider(val provider: ProviderUpdate) : ProfileScreenEvent()
    object ProfileScreen : ProfileScreenEvent()
    object Authenticate : ProfileScreenEvent()
    object Update : ProfileScreenEvent()
    object UpdateProviderEvent : ProfileScreenEvent()
}
