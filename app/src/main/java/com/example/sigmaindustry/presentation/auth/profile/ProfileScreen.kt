package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sigmaindustry.presentation.auth.profile.edit.EditProfileView
import com.example.sigmaindustry.presentation.auth.profile.edit.EditProviderProfileView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ProfileScreen(
    state: State<ProfileScreenState>,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
) {

    Column(
        horizontalAlignment = CenterHorizontally
    ) {
        when (state.value.token) {
            null -> {
                Text("Wait", modifier = Modifier.align(alignment = CenterHorizontally))
                LaunchedEffect(null) {
                    event(ProfileScreenEvent.Authenticate)
                }

            }
            else -> {
                Column(
                    horizontalAlignment = CenterHorizontally
                ) {
                    if (state.value.token == "") {
                        state.value.token = null
                        logOut()
                    }
                    val openEditDialog = remember { mutableStateOf(0) }
                    when (openEditDialog.value) {
                        0 -> {
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .padding(16.dp),
                                horizontalAlignment = CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(30.dp))
                                // User information
                                Box(
                                    modifier = Modifier
                                        .size(144.dp)
                                        .clip(CircleShape)
                                ) {
                                    AsyncImage(
                                        model = state.value.authenticateResponse?.user?.photoUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "${state.value.authenticateResponse?.user?.firstName} ${state.value.authenticateResponse?.user?.lastName}",
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = state.value.authenticateResponse?.user?.email
                                        ?: "Email not available",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray,
                                        letterSpacing = 0.8.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp), color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // User details
                                Text(
                                    text = "Born in ${state.value.authenticateResponse?.user?.birthDate ?: "N/A"}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        letterSpacing = 0.8.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = state.value.authenticateResponse?.user?.sex
                                        ?: "Gender not specified",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        letterSpacing = 0.8.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp), color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                // Provider information
                                if(state.value.authenticateResponse?.provider != null) {
                                    state.value.authenticateResponse!!.provider.let { provider ->
                                        Text(
                                            text = "Provider info:",
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Spacer(modifier = Modifier.height(12.dp))

                                        Text(
                                            text = provider.businessName,
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(1.dp), color = Color.Gray
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                        Text(
                                            text = provider.description,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(1.dp), color = Color.Gray
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "Phone: +380${provider.phoneNumber}",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(1.dp), color = Color.Gray
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "City: ${provider.city}",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(1.dp), color = Color.Gray
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "Work time: ${provider.workTime}",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                color = Color.Black,
                                                letterSpacing = 0.8.sp
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                // Buttons
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(onClick = {
                                        openEditDialog.value = 1
                                    }) {
                                        Text("Edit Profile")
                                    }
                                    if(state.value.authenticateResponse?.provider != null) {
                                        Button(onClick = {
                                                 GlobalScope.launch {
                                                     delay(2000)
                                                     event(ProfileScreenEvent.UpdateProviderServicesEvent)
                                                     openEditDialog.value = 2
                                                 }
                                        }) {
                                            Text("Watch my services")
                                        }
                                    }
                                    Button(onClick = {
                                        logOut()
                                    }) {
                                        Text("Log Out")
                                    }
                                }
                            }

                        }


                        1 -> {
                            if (state.value.authenticateResponse?.provider != null && state.value.token != null) {
                                EditProviderProfileView(
                                    onUpdateRequest = { openEditDialog.value = 0 },
                                    user = state.value.authenticateResponse!!.user,
                                    provider = state.value.authenticateResponse!!.provider,
                                    token = state.value.token,
                                    event = event
                                ) {
                                    logOut()
                                }
                            } else if (state.value.authenticateResponse?.user != null && state.value.token != null) {
                                EditProfileView(
                                    onUpdateRequest = { openEditDialog.value = 0 },
                                    user = state.value.authenticateResponse!!.user,
                                    token = state.value.token,
                                    event = event
                                ) {
                                    logOut()
                                }
                            }

                        }

                        2 -> {
                            if (state.value.authenticateResponse?.provider != null && state.value.token != null) {
                                EditProviderProfileView(
                                    onUpdateRequest = { openEditDialog.value = 0 },
                                    user = state.value.authenticateResponse!!.user,
                                    provider = state.value.authenticateResponse!!.provider,
                                    token = state.value.token,
                                    event = event
                                ) {
                                    logOut()
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
