package com.example.sigmaindustry.presentation.auth.profile

sealed class ProfileScreenEvent{
    data class UpdateToken(val token: String) : ProfileScreenEvent()
    object ProfileScreen : ProfileScreenEvent()
    object Authenticate : ProfileScreenEvent()
}
