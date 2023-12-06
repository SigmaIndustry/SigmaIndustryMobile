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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.sigmaindustry.data.remote.dto.ProviderResponse
import com.example.sigmaindustry.data.remote.dto.ProviderUpdate
import com.example.sigmaindustry.data.remote.dto.User
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
fun EditProviderProfileView(
    onUpdateRequest: () -> Unit,
    token: String?,
    user: User,
    provider: ProviderResponse,
    event: KSuspendFunction1<ProfileScreenEvent, Unit>,
    logOut: () -> Unit
) {
    var error by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf(user.firstName) }
    var lastName by remember { mutableStateOf(user.lastName) }
    var isValidFirstName by remember { mutableStateOf(false) }
    var isValidLastName by remember { mutableStateOf(false) }

    var businessName by remember { mutableStateOf(provider.businessName) }
    var description by remember { mutableStateOf(provider.description) }
    var isValidBusinessName by remember { mutableStateOf(false) }
    var isValidDescription by remember { mutableStateOf(false) }

    var phoneNumber by remember { mutableStateOf(provider.phoneNumber) }
    var city by remember { mutableStateOf(provider.city) }
    var isValidCity by remember { mutableStateOf(false) }
    var isValidPhoneNumber by remember { mutableStateOf(false) }

    var workTime by remember { mutableStateOf(provider.workTime) }
    var isValidWorkTime by remember { mutableStateOf(false) }

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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        OutlinedTextField(
            value = businessName,
            onValueChange = { input ->
                businessName = input
                isValidBusinessName = input.isNotEmpty()
            },
            label = { Text("Business name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = { input ->
                description = input
                isValidDescription = input.isNotEmpty()
            },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = "+380 $phoneNumber",
            onValueChange = { input ->
                phoneNumber = input.slice(5..input.length-1)
                isValidPhoneNumber = input.isNotEmpty()
            },
            label = { Text("Phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = city,
            onValueChange = { input ->
                city = input
                isValidCity = input.isNotEmpty()
            },
            label = { Text("City") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = workTime,
            onValueChange = { input ->
                workTime = input
                isValidWorkTime = input.isNotEmpty()
            },
            label = { Text("Work time") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

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
                        birthDate.isNotEmpty(),
                        businessName.isNotEmpty(),
                        description.isNotEmpty(),
                        phoneNumber.isNotEmpty(),
                        city.isNotEmpty(),
                        workTime.isNotEmpty()
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
                            error += "birth day, "
                        }
                        if (!isValid[3]) {
                            error += "business name, "
                        }
                        if (!isValid[4]) {
                            error += "description, "
                        }
                        if (!isValid[5]) {
                            error += "phone number, "
                        }
                        if (!isValid[6]) {
                            error += "city, "
                        }
                        if (!isValid[7]) {
                            error += "work time."
                        }
                        return@Button
                    }

                    GlobalScope.launch {
                        event(
                            ProfileScreenEvent.UpdateProvider(
                                ProviderUpdate(
                                    token = token ?: "",
                                    firstName,
                                    lastName,
                                    birthDate,
                                    businessName,
                                    description,
                                    phoneNumber, city, workTime
                                )
                            )
                        )
                        event(ProfileScreenEvent.UpdateProviderEvent)
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
