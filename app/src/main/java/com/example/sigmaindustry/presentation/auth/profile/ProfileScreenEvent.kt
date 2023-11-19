package com.example.sigmaindustry.presentation.auth.profile

import com.example.sigmaindustry.data.remote.dto.UserUpdate

sealed class ProfileScreenEvent{
    data class UpdateToken(val token: String) : ProfileScreenEvent()
    data class UpdateUser(val user: UserUpdate) : ProfileScreenEvent()
    object ProfileScreen : ProfileScreenEvent()
    object Authenticate : ProfileScreenEvent()
    object Update : ProfileScreenEvent()
}
