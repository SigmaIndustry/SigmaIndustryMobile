package com.example.sigmaindustry.presentation.auth.signIn

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.LoginRequest
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.util.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    event: (LoginEvent) -> Unit,
    toProfile: () -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding1)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp,top = 70.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = error,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val valid = listOf(
                    Validator.validateMail(email),
                    Validator.validatePassword(password)
                )

                if (valid.contains(false)) {
                    error = "Error: "
                    if (!valid[0]) {
                        error += "invalid email, "
                    }
                    if (!valid[1]) {
                        error += "invalid password, "
                    }
                    return@Button
                }
                loading = true
                GlobalScope.launch {

                    event(LoginEvent.UpdateLoginRequest(LoginRequest(email, password)))
                    event(LoginEvent.Login)
                    delay(2000)
                    withContext (Dispatchers.Main) {
                        toProfile()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Log in", fontSize = 18.sp)
        }

        if (!loading) return

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp).padding(bottom = 70.dp, top = 70.dp),
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}
