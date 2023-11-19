package com.example.sigmaindustry.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.domain.repository.ServicesRepository
import com.example.sigmaindustry.domain.usecases.news.SearchServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchServicesUseCase: SearchServices,
    private val serviceRepository: ServicesRepository
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun changeServiceCategory(s: Service): Service {
        return s.copy(category = serviceRepository.getCategories()[s.category] ?: "Unknown")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchService()
            }
        }
    }

    private fun searchService() {
        val services = searchServicesUseCase(
            searchQuery = _state.value.searchQuery,
        )
        _state.value = _state.value.copy(services = services)
    }


}