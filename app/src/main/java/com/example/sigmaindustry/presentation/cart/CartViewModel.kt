package com.example.sigmaindustry.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.HistoryResponse
import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val serviceProvider: ServicesRepository,
    val readToken: ReadToken
) : ViewModel() {
    var _state = mutableStateOf(CartState(HistoryResponse(listOf())))
        private set

    val state: State<CartState> = _state

    @OptIn(DelicateCoroutinesApi::class)
    fun onEvent(event: CartViewEvent) {
        when (event) {
            is CartViewEvent.Load -> {
                GlobalScope.launch {
                    val token = readToken()?.split(":") ?: return@launch
                    val resp = serviceProvider.getHistory(token[0])
                    _state.value = _state.value.copy(history = resp)
                }
            }
        }
    }

}
