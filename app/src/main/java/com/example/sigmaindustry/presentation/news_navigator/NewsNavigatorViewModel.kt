package com.example.sigmaindustry.presentation.news_navigator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.domain.usecases.token.ReadTokenUseCase
import com.example.sigmaindustry.domain.usecases.token.SaveToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsNavigatorViewModel @Inject constructor(
    private val readTokenUseCase: ReadTokenUseCase,
    private val saveTokenUseCase: SaveToken
) : ViewModel() {
    private var _state = mutableStateOf(NewsNavigatorState())
    val state: State<NewsNavigatorState> = _state
     fun onEvent(event: NewsNavigatorEvent) {
        when (event) {
            is NewsNavigatorEvent.GetToken -> {
                GlobalScope.launch {
                    readToken()
                }
            }
            is NewsNavigatorEvent.SaveToken -> {
                setTokenNull()
            }
            is NewsNavigatorEvent.ChangeTopBarTitle -> {
                _state.value = _state.value.copy(topBarTitle = event.newTitle)
            }
            is NewsNavigatorEvent.ChangeTopBarNavIcon -> {
                _state.value = _state.value.copy(topBarNavIcon = event.newIcon, topBarIconDesc = event.newIconDesc,
                    onClick = { event.onClick() })
            }
            is NewsNavigatorEvent.ClearTopBarIcon -> {
                _state.value = _state.value.copy(topBarNavIcon = null)
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
     fun readToken() {
        GlobalScope.launch {
            val token = async { readTokenUseCase() }
            _state.value = _state.value.copy(token = token.await())
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
     fun setTokenNull() {
        GlobalScope.launch {
            saveTokenUseCase(null)
        }
    }
}
