package com.example.sigmaindustry.presentation.auth.selectAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class AuthType (val type: Int) {
    object None: AuthType(0)
    object LogIn: AuthType(1)
    object LogUp: AuthType(2)
    object Loggined: AuthType(3)
}

data class SelectAuthState(
    val checkedAuthType: AuthType
)

sealed class SelectAuthEvent {
    data class ChangeAuthType(val type: AuthType) : SelectAuthEvent()
}

@HiltViewModel
class SelectAuthViewModel @Inject constructor(
) : ViewModel() {
    private var _state = mutableStateOf(SelectAuthState(AuthType.None))
    val state: State<SelectAuthState> = _state

    fun onEvent(event: SelectAuthEvent) {
        when (event) {
            is SelectAuthEvent.ChangeAuthType -> {_state.value = _state.value.copy(checkedAuthType = event.type)}
        }

    }
}
