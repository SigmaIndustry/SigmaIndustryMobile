package com.example.sigmaindustry.presentation.auth.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun ProfileScreen(
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
                event(ProfileScreenEvent.Authenticate)
                Column {
                    AsyncImage(model = state.authenticateResponse?.user?.photoUrl,  contentDescription = null,
                        contentScale = ContentScale.Crop)
                    Text("Hello ${state.authenticateResponse?.user?.firstName ?: "null"} ${state.authenticateResponse?.user?.lastName ?: "null"}")

                    Text("Token is ${state.token}")
                    Row {
                        Button(onClick = { event(ProfileScreenEvent.ProfileScreen) }) {
                            Text("Update")
                        }
                        Button(onClick = { logOut() }) {
                            Text("LogOut")
                        }
                    }
                }
            }
        }
    }
}