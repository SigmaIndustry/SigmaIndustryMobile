package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.sigmaindustry.extension.observeLifecycle
import kotlin.math.log

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
    state: ProfileScreenState,
    event: (ProfileScreenEvent) -> Unit,
    logOut: () -> Unit
){
    Column {
        when (state.token) {
            null -> {
                Text("Wait")
                event(ProfileScreenEvent.ProfileScreen)
            }
            else -> {
                if (state.token == "") {
                    logOut()
                }
                Text("Token is ${state.token}")
                Row {
                    Button(onClick = {event(ProfileScreenEvent.ProfileScreen)}) {
                        Text("Update")
                    }
                    Button(onClick = {logOut()}) {
                        Text("LogOut")
                    }
                }
            }
        }
    }
}