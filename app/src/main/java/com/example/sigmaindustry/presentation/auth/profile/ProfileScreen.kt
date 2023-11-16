package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
){
    var tokenString = ""
    Column {

        Button({}) {
            Text(text = "get token")
        }

        Text(text = tokenString)
    }
}