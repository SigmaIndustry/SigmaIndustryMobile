package com.example.sigmaindustry.presentation.auth.selectAuth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.presentation.Dimens


@Composable
fun SelectAuthScreen(
    navigateToLogin: () -> Unit
) {

    Column(

        modifier = Modifier
            .padding(top = Dimens.MediumPadding1, start = Dimens.MediumPadding1, end = Dimens.MediumPadding1)
            .statusBarsPadding()
    ) {
        Button(
            onClick = {
                navigateToLogin.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(text = "Log in", fontSize = 40.sp)
        }
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(text = "Sign up", fontSize = 40.sp)
        }
    }
}