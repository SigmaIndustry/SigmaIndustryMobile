package com.example.sigmaindustry.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigmaindustry.data.remote.ServicesApi
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.news.RateSenderService
import com.example.sigmaindustry.domain.usecases.token.ReadToken
import com.example.sigmaindustry.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val tokenReader: ReadToken,
    private val rateSenderUseCase: RateSenderService,
    private val servicesRepository: ServicesRepository
) : ViewModel() {
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    var showDialog by mutableStateOf(false)

    fun changeServiceCategory(s: Service): Service {
        return s.copy(category = servicesRepository.getCategories()[s.category] ?: "Unknown")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.ShowDialog -> {
                showDialog = true
            }
            is DetailsEvent.HideDialog -> {
                showDialog = false
            }
            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

            is DetailsEvent.SendOrder -> {
                GlobalScope.launch {
                    tokenReader()?.let {

                    }
                }
            }

            is DetailsEvent.SendRate -> {
                GlobalScope.launch {
                    tokenReader()?.let {
                        rateSenderUseCase(
                            feedback = event.feedback,
                            token = it,
                            rating = event.rating,
                            serviceId = event.serviceId
                        )
                    }
                }
            }
        }
    }


}