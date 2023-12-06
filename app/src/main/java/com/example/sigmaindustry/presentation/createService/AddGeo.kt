package com.example.sigmaindustry.presentation.createService

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.AddGeolocation
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.domain.usecases.AddGeoUseCase
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import com.example.sigmaindustry.presentation.Dimens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class AddGeoEvent{
    data class ChangeLatitude(val newLatitude: String) : AddGeoEvent()
    data class ChangeLongitude(val newLongitude: String) : AddGeoEvent()
    object Send : AddGeoEvent()
}

data class AddGeoState(
    val latitude: String = "0.0",
    val longitude: String = "0.0",
    val service: Service? = null
)

@HiltViewModel
class AddGeoViewModel @Inject constructor(
    val addGeoUseCase: AddGeoUseCase,
    val readTokenUseCase: ReadTokenUseCase
): ViewModel() {
    val _state = mutableStateOf(AddGeoState())
    val state: State<AddGeoState> = _state
    var errorHandler: (String) -> Unit = {}


    @OptIn(DelicateCoroutinesApi::class)
    fun onEvent(event: AddGeoEvent) {
        when (event){
            is AddGeoEvent.ChangeLongitude -> {
                _state.value = _state.value.copy(longitude = event.newLongitude)
            } is AddGeoEvent.ChangeLatitude -> {
                _state.value = _state.value.copy(latitude = event.newLatitude)
            } is AddGeoEvent.Send -> {
                try {
                    val long = _state.value.latitude.toDouble()
                    val lati = _state.value.latitude.toDouble()
                    GlobalScope.launch {
                        val token = readTokenUseCase()
                        val sId = state.value.service?.id
                        if (sId != null) {
                            addGeoUseCase(AddGeolocation(token!!, sId, lati, long))
                        }
                    }
                } catch (e: Exception) {
                    errorHandler("Invalid values")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGeoView(
    viewModel: AddGeoViewModel,
    state: State<AddGeoState>,
) {
    Column(modifier = Modifier.padding(horizontal = Dimens.MediumPadding1)) {
        Text("Latitude: ${state.value.latitude}, Longitude: ${state.value.longitude}")
        OutlinedTextField(viewModel.state.value.latitude, {
            viewModel.onEvent(AddGeoEvent.ChangeLatitude(it))
        })
        OutlinedTextField(viewModel.state.value.longitude, {viewModel.onEvent(AddGeoEvent.ChangeLongitude(it))})
        OutlinedButton(onClick = { viewModel.onEvent(AddGeoEvent.Send)}) {
            Text("Send")
        }
    }
}