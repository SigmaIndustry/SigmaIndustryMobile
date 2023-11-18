package com.example.sigmaindustry.presentation.auth.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlin.reflect.KSuspendFunction1


@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
){

    Column {
        when (state.token) {
            null -> {
                Text("Wait")

                    LaunchedEffect(state) {
                        delay(2000)
                        event(ProfileScreenEvent.Authenticate)
                    }

            }
            else -> {
                if (state.token == "") {
                    state.token = null
                    logOut()
                }
                Column {
                    AsyncImage(model = state.authenticateResponse?.user?.photoUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(shape = CircleShape))

                    Text(
                        text = (state.authenticateResponse?.user?.firstName ?: "null") +
                                " " +
                                (state.authenticateResponse?.user?.lastName ?: "null"),
                        style = TextStyle(
                            fontSize = 22.sp),
                            maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = state.authenticateResponse?.user?.email ?: "null",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray,
                            letterSpacing = (0.8).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text("Token is ${state.token}")
                    Row {
//                        Button(onClick = { event(ProfileScreenEvent.ProfileScreen) }) {
//                            Text("Update")
//                        }
                        Button(onClick = {
                            state.token = null
                            logOut()
                        }) {
                            Text("LogOut")
                        }
                    }
                }
            }
        }
    }
}