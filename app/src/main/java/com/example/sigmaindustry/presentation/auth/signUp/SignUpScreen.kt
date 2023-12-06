package com.example.sigmaindustry.presentation.auth.signUp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.Role
import com.example.sigmaindustry.data.remote.dto.Sex
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.util.Validator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun SignUpScreen(
    state: SignUpState,
    event: (SignUpEvent) -> Unit,
    viewModel: SignUpViewModel,
    toProfile: () -> Unit
) {
    var birthDate by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    var loading by remember { mutableStateOf(false) }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            birthDate = "$year-$month-$day"
        }, mYear, mMonth, mDay
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding1)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User Information
        OutlinedTextField(
            value = state.user.email,
            onValueChange = { viewModel.updateUser(state.user.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.user.password,
            onValueChange = { viewModel.updateUser(state.user.copy(password = it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.user.firstName,
            onValueChange = { viewModel.updateUser(state.user.copy(firstName = it)) },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.user.lastName,
            onValueChange = { viewModel.updateUser(state.user.copy(lastName = it)) },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Birthdate and Sex
        Button(
            onClick = { mDatePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Pick birth date: $birthDate", color = Color.White)
        }

        Text("Choose your sex")
        Row {
            // Male Button
            Button(
                onClick = { viewModel.updateUser(state.user.copy(sex = Sex.Male.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.sex == Sex.Male.toString) Color(0xFF6650a4) else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                Text("Male")
            }

            // Female Button
            Button(
                onClick = { viewModel.updateUser(state.user.copy(sex = Sex.Female.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.sex == Sex.Female.toString) Color(0xFF6650a4) else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                Text("Female")
            }
        }

        // Choose your role
        Text("Choose your role")
        Row {
            // User Button
            Button(
                onClick = { viewModel.updateUser(state.user.copy(role = Role.User.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.role == Role.User.toString) Color(0xFF6650a4) else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                Text("User")
            }

            // Service Provider Button
            Button(
                onClick = { viewModel.updateUser(state.user.copy(role = Role.ServiceProvider.toString)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.user.role == Role.ServiceProvider.toString) Color(0xFF6650a4) else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                Text("Service provider")
            }
        }

        // Service Provider Information
        if (state.user.role == Role.ServiceProvider.toString) {
            OutlinedTextField(
                value = state.provider.businessName,
                onValueChange = { viewModel.updateProvider(state.provider.copy(businessName = it)) },
                label = { Text("Business name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = state.provider.description,
                onValueChange = { viewModel.updateProvider(state.provider.copy(description = it)) },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = state.provider.phoneNumber,
                onValueChange = { viewModel.updateProvider(state.provider.copy(phoneNumber = it)) },
                label = { Text("Phone number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = state.provider.city,
                onValueChange = { viewModel.updateProvider(state.provider.copy(city = it)) },
                label = { Text("City") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = state.provider.workTime,
                onValueChange = { viewModel.updateProvider(state.provider.copy(workTime = it)) },
                label = { Text("Work time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        // Display Error
        Text(text = error, color = Color.Red, modifier = Modifier.padding(bottom = 16.dp))

        // Sign-up Button
        Button(
            onClick = {
                val isValid = listOf(
                    Validator.validateMail(state.user.email),
                    Validator.validatePassword(state.user.password),
                    state.user.firstName.isNotEmpty(),
                    state.user.lastName.isNotEmpty(),
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
                error = ""
                val validProvider = listOf(
                    state.user.role == Role.ServiceProvider.toString,
                    state.provider.city.isNotEmpty(),
                    state.provider.description.isNotEmpty(),
                    state.provider.phoneNumber.isNotEmpty(),
                    state.provider.workTime.isNotEmpty(),
                    state.provider.businessName.isNotEmpty())


                    if (validProvider.contains(false)) {
                        viewModel.updateProvider(state.provider.copy(email = state.user.email))
                        event(SignUpEvent.RegisterProvider)
                    } else {
                        error = "Error from: "
                        if (!validProvider[1]) {
                            error += "city, "
                        }
                        if (!validProvider[2]) {
                            error += "description, "
                        }
                        if (!validProvider[3]) {
                            error += "phone number, "
                        }
                        if (!validProvider[4]) {
                            error += "worktime, "
                        }
                        if (!validProvider[5]) {
                            error += "business name."
                        }

                    }
                loading = true
                GlobalScope.launch {
                    viewModel.updateUser(state.user.copy(birthDate = birthDate,
                        photoUrl = "https://i.ibb.co/jwKccRz/profile-Picture.jpg"))
                    event(SignUpEvent.SignUp)

                    delay(6000)
                    withContext (Dispatchers.Main) {
                        toProfile()
                    }

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Sign up", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))


        if (!loading) return

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp).padding(bottom = 70.dp, top = 70.dp),
            color = MaterialTheme.colorScheme.secondary,
        )

        // Display Token (You may customize this part based on your needs)
      //  Text(text = state.loginResponse.token)
    }
}
