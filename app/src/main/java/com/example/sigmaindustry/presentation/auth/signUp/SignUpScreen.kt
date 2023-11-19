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
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.util.Validator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun SignUpScreen(
    state: SignUpState,
    event: (SignUpEvent) -> Unit,
    viewModel: SignUpViewModel,
    toProfile: () -> Unit
) {

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
            value = state.user.email,
            onValueChange = { viewModel.updateUser(state.user.copy(email = it)) },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = state.user.password,
            onValueChange = { viewModel.updateUser(state.user.copy(password = it)) },
            label = { Text("Password") },
        )
        OutlinedTextField(
            value = state.user.firstName,
            onValueChange = { viewModel.updateUser(state.user.copy(firstName = it)) },
            label = { Text("First Name") },
        )

        OutlinedTextField(
            value = state.user.lastName,
            onValueChange = { viewModel.updateUser(state.user.copy(lastName = it)) },
            label = { Text("Last Name") },
        )

        Button(onClick = { mDatePickerDialog.show() }) {
            Text(text = "Pick birth date: $birthDate", color = Color.White)
        }
        Text("Choose your sex")
        Row()
        {
            Button(
                onClick = { viewModel.updateUser(state.user.copy(sex = Sex.Male.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.sex == Sex.Male.toString) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {
                Text("Male")
            }
            Button(
                onClick = { viewModel.updateUser(state.user.copy(sex = Sex.Female.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.sex == Sex.Female.toString) Color(
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
                onClick = { viewModel.updateUser(state.user.copy(role = Role.User.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.role == Role.User.toString) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {

                Text("User")
            }
            Button(
                onClick = { viewModel.updateUser(state.user.copy(role = Role.ServiceProvider.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.role == Role.ServiceProvider.toString) Color(
                        0xFF6650a4
                    ) else Color.Gray
                )
            ) {

                Text("Service provider")
            }
        }
        if (state.user.role == Role.ServiceProvider.toString) {
            OutlinedTextField(
                value = state.provider.businessName ,
                onValueChange = { viewModel.updateProvider(state.provider.copy(businessName = it)) },
                label = { Text("Business name") },
            )
            OutlinedTextField(
                value = state.provider.description,
                onValueChange = { viewModel.updateProvider(state.provider.copy(description = it)) },
                label = { Text("Description") },
            )
            OutlinedTextField(
                value = state.provider.phoneNumber,
                onValueChange = { viewModel.updateProvider(state.provider.copy(phoneNumber = it)) },
                label = { Text("Phone number") },
            )
            OutlinedTextField(
                value = state.provider.city,
                onValueChange = { viewModel.updateProvider(state.provider.copy(city = it)) },
                label = { Text("City") },
            )
            OutlinedTextField(
                value = state.provider.workTime,
                onValueChange = { viewModel.updateProvider(state.provider.copy(workTime = it)) },
                label = { Text("Work time") },
            )
        }

        Button(
            onClick = {
                val valid = Validator.validateMail(state.user.email)
                        && Validator.validatePassword(state.user.password)
                        && state.user.firstName.isNotEmpty()
                        && state.user.lastName.isNotEmpty()
                        && birthDate.isNotEmpty()
                if (!valid) {
                    println("Invalid")
                    return@Button
                }
                val validProvider = state.provider.businessName.isNotEmpty()
                        && state.provider.city.isNotEmpty()
                        && state.provider.description.isNotEmpty()
                        && state.provider.phoneNumber.isNotEmpty()
                        && state.provider.workTime.isNotEmpty()
                        && state.user.role == Role.ServiceProvider.toString
                GlobalScope.launch {
                    viewModel.updateUser(state.user.copy(birthDate = birthDate,
                        photoUrl = "https://i.ibb.co/jwKccRz/profile-Picture.jpg"))
                    event(SignUpEvent.SignUp)

                    delay(2000)

                    println("Valid")
                    if (validProvider) {
                        viewModel.updateProvider(state.provider.copy(email = state.user.email))
                        event(SignUpEvent.RegisterProvider)
                    }
                    toProfile()
                }

            },
        ) {
            Text(text = "Sign up", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(text = state.loginResponse.token)
    }
}
