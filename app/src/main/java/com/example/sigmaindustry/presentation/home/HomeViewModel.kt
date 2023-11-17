package com.example.sigmaindustry.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sigmaindustry.domain.usecases.news.GetServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getServices: GetServices
): ViewModel() {

    var state = mutableStateOf(HomeState())
        private set

    val services = getServices().cachedIn(viewModelScope)

}