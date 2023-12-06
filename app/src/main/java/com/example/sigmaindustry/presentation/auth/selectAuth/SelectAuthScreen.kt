package com.example.sigmaindustry.presentation.auth.selectAuth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    viewModel: SelectAuthViewModel,
    event: (SelectAuthEvent) -> Unit,
    toLogin: () -> Unit,
    toSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
    ) {

        ChangeButtons(
            toLogin = toLogin, toSignUp =
            toSignUp
        )
    }
}


@Composable
fun ChangeButtons(
    toLogin: () -> Unit,
    toSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                toLogin()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Log in", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                toSignUp()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Sign up", fontSize = 20.sp)
        }
    }
}
