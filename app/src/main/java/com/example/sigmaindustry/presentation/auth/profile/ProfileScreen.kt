package com.example.sigmaindustry.presentation.auth.profile

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.util.Validator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import kotlin.reflect.KSuspendFunction1


@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
) {

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.padding(Dimens.MediumPadding1)
    ) {
        when (state.token) {
            null -> {
                Text("Wait", modifier = Modifier.align(alignment = CenterHorizontally))

                LaunchedEffect(state) {
                    delay(2000)
                    event(ProfileScreenEvent.Authenticate)
                }

            }

            else -> {
                Column(
                    horizontalAlignment = CenterHorizontally
                ) {


                    if (state.token == "") {
                        state.token = null
                        logOut()
                    }
                    var openAlertDialog = remember { mutableStateOf(false) }
                    when (openAlertDialog.value) {
                        false -> {
                            Column(
                                modifier =
                                Modifier.verticalScroll(rememberScrollState()),
                                horizontalAlignment = CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = state.authenticateResponse?.user?.photoUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(144.dp)
                                        .clip(shape = CircleShape)
                                )

                                Text(
                                    text = (state.authenticateResponse?.user?.firstName) +
                                            " " +
                                            (state.authenticateResponse?.user?.lastName),
                                    style = TextStyle(
                                        fontSize = 44.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = state.authenticateResponse?.user?.email ?: "null",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        color = Color.Gray,
                                        letterSpacing = (0.8).sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Born in " + state.authenticateResponse?.user?.birthDate,
                                    style = TextStyle(
                                        fontSize = 28.sp,
                                        color = Color.Black,
                                        letterSpacing = (0.8).sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = state.authenticateResponse?.user?.sex ?: "null",
                                    style = TextStyle(
                                        fontSize = 28.sp,
                                        color = Color.Black,
                                        letterSpacing = (0.8).sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(40.dp))
                                if (state.authenticateResponse?.provider != null) {
                                    Text(
                                        text = "Provider info:",
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(
                                        text = state.authenticateResponse.provider.businessName,
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = state.authenticateResponse.provider.description,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Phone: +380" + state.authenticateResponse.provider.phoneNumber,
                                        style = TextStyle(
                                            fontSize = 28.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "City: " + state.authenticateResponse.provider.city,
                                        style = TextStyle(
                                            fontSize = 28.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Work time: " + state.authenticateResponse.provider.workTime,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            color = Color.Black,
                                            letterSpacing = (0.8).sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                //Text("Token is ${state.token}")
                                Row {
                                    Button(onClick = {

                                        openAlertDialog.value = true
                                    }) {
                                        Text("Edit profile")
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Button(onClick = {
                                        state.token = null
                                        logOut()
                                    }) {
                                        Text("LogOut")
                                    }
                                }
                            }
                        }

                        true -> {
                            if (state.authenticateResponse?.user != null && state.token != null) {
                                EditProfileView(
                                    onUpdateRequest = { openAlertDialog.value = false },
                                    user = state.authenticateResponse.user,
                                    token = state.token,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileView(
    onUpdateRequest: () -> Unit,
    token: String?,
    user: User,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
){

    var error by remember {
        mutableStateOf("")
    }

    var email by remember { mutableStateOf(user.email) }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf(user.firstName) }
    var lastName by remember { mutableStateOf(user.lastName) }
    var isValidEmail by remember { mutableStateOf(false) }
    var isValidPassword by remember { mutableStateOf(false) }
    var isValidFirstName by remember { mutableStateOf(false) }
    var isValidLastName by remember { mutableStateOf(false) }
    val emailRequiredChars = setOf('@', '.')

    var birthDate by remember { mutableStateOf(user.birthDate) }

    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()


    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            birthDate = "$year-$month-$day"
        }, mYear, mMonth, mDay
    )

    Column(
         horizontalAlignment = CenterHorizontally,
    ) {
        Text(
            text = "Remember you need to sign in again if you update profile"
        )
        OutlinedTextField(
            value = email,
            onValueChange = { input ->
                email = input
                isValidEmail = input.isNotEmpty() && input.all(emailRequiredChars::contains)
            },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = { input ->
                firstName = input
                isValidFirstName = input.isNotEmpty()
            },
            label = { Text("First Name") },
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { input ->
                lastName = input
                isValidLastName = input.isNotEmpty()
            },
            label = { Text("Last Name") },
        )

        Button(onClick = { mDatePickerDialog.show() }) {
            Text(text = "Pick birth date: $birthDate", color = Color.White)
        }

        OutlinedTextField(
            value = password,
            onValueChange = { input ->
                password = input
                isValidPassword = input.length >= 6
            },
            label = { Text("Password") },
        )
        Text(error)
        Row() {
            Button(
                onClick = {
                    val isValid = listOf(
                        Validator.validateMail(email),
                        Validator.validatePassword(password),
                        firstName.isNotEmpty(),
                        lastName.isNotEmpty(),
                        birthDate.isNotEmpty())
                    if (isValid.contains(false)) {
                        error = "Error from: "
                        if (!isValid[0]) {
                            error += "email, "
                        }
                        if (!isValid[1]) {
                            error += "password, "
                        }
                        if (!isValid[2]) {
                            error += "first name, "
                        }
                        if (!isValid[3]) {
                            error += "last name, "
                        }
                        if (!isValid[4]) {
                            error += "birth day."
                        }
                        return@Button
                    }

                    GlobalScope.launch {
                        event(
                            ProfileScreenEvent.UpdateUser(
                                UserUpdate(
                                    token = token ?: "",
                                    email,
                                    password,
                                    firstName,
                                    lastName,
                                    birthDate
                                )
                            )
                        )
                        event(ProfileScreenEvent.Update)
                        logOut()
                        onUpdateRequest()
                    }
                }
            ) {
                Text(text = "Update")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                        onUpdateRequest()
                }
            ) {
                Text(text = "Cancel")
            }
        }
    }
}