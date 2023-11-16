package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.sigmaindustry.extension.observeLifecycle

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
    state: ProfileScreenState,
    navigateToSelectAuth: () -> Unit
){
    viewModel.observeLifecycle(LocalLifecycleOwner.current.lifecycle)

    if(state.token != null) {
        Column {

            Text(text = state.token)
        }
    }
    else{
        navigateToSelectAuth.invoke()
    }
}