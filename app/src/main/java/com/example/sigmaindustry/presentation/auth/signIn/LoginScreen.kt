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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    event: (LoginEvent) -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            onValueChange = { input ->
                email = input
            },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { input ->
                password = input
            },
            label = { Text("Password") },
        )
        Button(onClick = {
            event(LoginEvent.UpdateLoginRequest(LoginRequest(email, password)))
            event(LoginEvent.Login)
        }) {
            Text(text = "Log in", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        state.loginResponse.let {
            Text(text = it.token )
        }
    }
}

