package com.example.sigmaindustry.presentation.auth.profile.edit

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.data.remote.dto.UserUpdate
import com.example.sigmaindustry.presentation.auth.profile.ProfileScreenEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import kotlin.reflect.KSuspendFunction1

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun EditProfileView(
    onUpdateRequest: () -> Unit,
    token: String?,
    user: User,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
) {
    var error by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf(user.firstName) }
    var lastName by remember { mutableStateOf(user.lastName) }
    var isValidFirstName by remember { mutableStateOf(false) }
    var isValidLastName by remember { mutableStateOf(false) }
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
        },
        mYear,
        mMonth,
        mDay
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Remember you need to sign in again if you update profile",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { input ->
                firstName = input
                isValidFirstName = input.isNotEmpty()
            },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { input ->
                lastName = input
                isValidLastName = input.isNotEmpty()
            },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { mDatePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Pick birth date: $birthDate", color = Color.White)
        }

        Text(error, modifier = Modifier.padding(bottom = 16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val isValid = listOf(
                        firstName.isNotEmpty(),
                        lastName.isNotEmpty(),
                        birthDate.isNotEmpty()
                    )
                    if (isValid.contains(false)) {
                        error = "Error from: "
                        if (!isValid[0]) {
                            error += "first name, "
                        }
                        if (!isValid[1]) {
                            error += "last name, "
                        }
                        if (!isValid[2]) {
                            error += "birth day."
                        }
                        return@Button
                    }

                    GlobalScope.launch {
                        event(
                            ProfileScreenEvent.UpdateUser(
                                UserUpdate(
                                    token = token ?: "",
                                    firstName,
                                    lastName,
                                    birthDate
                                )
                            )
                        )
                        event(ProfileScreenEvent.Update)
                        withContext (Dispatchers.Main) {
                            logOut()
                            onUpdateRequest()
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Update")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = { onUpdateRequest() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}
