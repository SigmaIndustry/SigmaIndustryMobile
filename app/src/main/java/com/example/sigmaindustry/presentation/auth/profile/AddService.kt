package com.example.sigmaindustry.presentation.auth.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class AddServiceState(
    val some: String = ""
)

sealed class AddServiceEvent {

}

@HiltViewModel
class AddServiceViewModel @Inject constructor(

) : ViewModel() {
    val _state = mutableStateOf(AddServiceState())
    val state: State<AddServiceState> = _state

    fun onEvent(event: AddServiceEvent){
        when (event) {
            else -> {}
        }
    }
}


@Composable
fun AddServiceView() {
    Column {

    }
}