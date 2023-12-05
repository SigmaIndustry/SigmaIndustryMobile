package com.example.sigmaindustry.presentation.news_navigator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsNavigatorViewModel @Inject constructor(
    private val readTokenUseCase: ReadTokenUseCase
) : ViewModel() {
    private var _state = mutableStateOf(NewsNavigatorState())
    val state: State<NewsNavigatorState> = _state
    suspend fun onEvent(event: NewsNavigatorEvent) {
        when (event) {
            is NewsNavigatorEvent.GetToken -> {
                readToken()
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun readToken() {
        GlobalScope.launch {
            val token = async { readTokenUseCase() }
            _state.value = _state.value.copy(token = token.await())
        }
    }
}
