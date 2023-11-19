package com.example.sigmaindustry.presentation.auth.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.navgraph.Route
import com.example.sigmaindustry.util.Validator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    event: (LoginEvent) -> Unit,
    toProfile: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it },
            label = { Text("Password") },
        )
        Text(error)
        Button(onClick = {
            val valid = listOf(Validator.validateMail(email),
                    Validator.validatePassword(password))
            if (valid.contains(false)) {
                error = "Error "
                if (!valid[0]) {
                    error += "email, "
                }
                if (!valid[1]) {
                    error += "password, "
                }
                return@Button
            }
            event(LoginEvent.UpdateLoginRequest(LoginRequest(email, password)))
            event(LoginEvent.Login)
            toProfile()
        }) {
            Text(text = "Log in", fontSize = 20.sp)
        }
    }
}

