package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
    state: ProfileScreenState,
    navigateToSelectAuth: () -> Unit
){

    Text(text = state.token ?: "null")
}