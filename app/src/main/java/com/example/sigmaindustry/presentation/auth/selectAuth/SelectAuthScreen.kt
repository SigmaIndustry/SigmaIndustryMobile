package com.example.sigmaindustry.presentation.auth.selectAuth

import android.transition.ChangeBounds
import androidx.activity.compose.BackHandler
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.auth.signIn.LoginScreen
import com.example.sigmaindustry.presentation.auth.signIn.LoginViewModel
import com.example.sigmaindustry.presentation.auth.signUp.SignUpScreen
import com.example.sigmaindustry.presentation.auth.signUp.SignUpViewModel
import com.example.sigmaindustry.presentation.navgraph.Route
import com.example.sigmaindustry.presentation.news_navigator.OnBackClickStateSaver


@Composable
fun SelectAuthScreen(
    viewModel: SelectAuthViewModel,
    event: (SelectAuthEvent) -> Unit,
    navController: NavController
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
        when (viewModel.state.value.checkedAuthType) {
            AuthType.None -> ChangeButtons(changeAuthType = {
                event(
                    SelectAuthEvent.ChangeAuthType(
                        it
                    )
                )
            })

            AuthType.LogUp -> {
                val logUpViewModel: SignUpViewModel = hiltViewModel()
                val state = logUpViewModel.state.value
                BackHandler(true) {
                    event(SelectAuthEvent.ChangeAuthType(AuthType.None))
                }
                SignUpScreen(
                    state = state,
                    event = logUpViewModel::onEvent
                )
            }

            AuthType.LogIn -> {
                val logInViewModel: LoginViewModel = hiltViewModel()
                val state = logInViewModel.state.value
                BackHandler(true) {
                    event(SelectAuthEvent.ChangeAuthType(AuthType.None))
                }
                LoginScreen(
                    state = state,
                    event = logInViewModel::onEvent
                )
            }
            AuthType.Loggined -> Text(text = "Hello world 3")
        }
    }
}


@Composable
fun ChangeButtons(changeAuthType: (AuthType) -> Unit) {
    Button(
        onClick = {
            changeAuthType(AuthType.LogIn)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(text = "Log in", fontSize = 40.sp)
    }
    Button(
        onClick = {
            changeAuthType(AuthType.LogUp)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(text = "Log up", fontSize = 40.sp)
    }
}