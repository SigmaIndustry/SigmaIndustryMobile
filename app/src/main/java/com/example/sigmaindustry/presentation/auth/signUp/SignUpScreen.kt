package com.example.sigmaindustry.presentation.auth.signUp

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.RegisterProvider
import com.example.sigmaindustry.data.remote.dto.Role
import com.example.sigmaindustry.data.remote.dto.Sex
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.presentation.Dimens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    state: SignUpState,
    event: (SignUpEvent) -> Unit,
    toProfile: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(false) }
    var isValidPassword by remember { mutableStateOf(false) }
    var isValidFirstName by remember { mutableStateOf(false) }
    var isValidLastName by remember { mutableStateOf(false) }
    val emailRequiredChars = setOf('@', '.')

    var bussinessName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var workTime by remember { mutableStateOf("") }

    var sex by remember { mutableStateOf(Sex.Male) }

    var role by remember { mutableStateOf(Role.User) }

    var birthDate by remember { mutableStateOf("") }

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
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { input ->
                email = input
                isValidEmail = input.isNotEmpty() && input.all(emailRequiredChars::contains)
            },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { input ->
                password = input
                isValidPassword = input.length >= 6
            },
            label = { Text("Password") },
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
        Text("Choose your sex")
        Row()
        {

            Button(
                onClick = {
                    sex = Sex.Male
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sex == Sex.Male) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {
                Text("Male")
            }
            Button(
                onClick = { sex = Sex.Female },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sex == Sex.Female) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {
                Text("Female")
            }
        }
        Text("Choose your role")
        Row()
        {
            Button(
                onClick = { role = Role.User },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == Role.User) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {

                Text("User")
            }
            Button(
                onClick = { role = Role.ServiceProvider },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == Role.ServiceProvider) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {

                Text("Service provider")
            }
        }
        if (role == Role.ServiceProvider) {
            OutlinedTextField(
                value = bussinessName,
                onValueChange = { input ->
                    bussinessName = input
                },
                label = { Text("Business name") },
            )
            OutlinedTextField(
                value = description,
                onValueChange = { input ->
                    description = input
                },
                label = { Text("Description") },
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { input ->
                    phoneNumber = input
                },
                label = { Text("Phone number") },
            )
            OutlinedTextField(
                value = city,
                onValueChange = { input ->
                    city = input
                },
                label = { Text("City") },
            )
            OutlinedTextField(
                value = workTime,
                onValueChange = { input ->
                    workTime = input
                },
                label = { Text("Work time") },
            )
        }

        Button(
            onClick = {

                GlobalScope.launch {
                    event(
                        SignUpEvent.UpdateSignUpRequest(
                            User(
                                email,
                                password,
                                firstName,
                                lastName,
                                birthDate,
                                if (sex == Sex.Male) {
                                    "M"
                                } else {
                                    "F"
                                },
                                "https://i.ibb.co/jwKccRz/profile-Picture.jpg",
                                if (role == Role.User) {
                                    "G"
                                } else {
                                    "P"
                                }
                            )
                        )
                    )
                    event(SignUpEvent.SignUp)
                    delay(2000)
                    if (role == Role.ServiceProvider) {
                        event(
                            SignUpEvent.UpdateProvider(
                                RegisterProvider(
                                    email,
                                    bussinessName,
                                    description,
                                    phoneNumber,
                                    city,
                                    workTime
                                )
                            )
                        )
                        event(SignUpEvent.RegisterProvider)
                    }
                    toProfile()
                }

            },
        ) {
            Text(text = "Sign up", fontSize = 20.sp)
        }


        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        state.loginResponse.let {
            Text(text = it.token)
        }
    }
}
