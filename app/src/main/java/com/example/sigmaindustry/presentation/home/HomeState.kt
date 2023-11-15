package com.example.sigmaindustry.presentation.home


data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)