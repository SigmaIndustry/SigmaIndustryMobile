package com.example.sigmaindustry.presentation.auth.profile

import android.provider.Settings.Global
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val readTokenUseCase: ReadToken
) : ViewModel(), DefaultLifecycleObserver {

    private var _state = mutableStateOf(ProfileScreenState())
    val state: State<ProfileScreenState> = _state


    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.UpdateToken -> {
                _state.value = _state.value.copy(token = event.token)
            }

            is ProfileScreenEvent.ProfileScreen -> {
                readToken()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
     fun readToken(){
        GlobalScope.launch {
            // TODO fix later illegal access to _state
            readTokenUseCase()?.let {_state.value = _state.value.copy(token = it)}
        }
    }
}