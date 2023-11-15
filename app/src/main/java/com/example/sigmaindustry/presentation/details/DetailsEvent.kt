package com.example.sigmaindustry.presentation.details

import com.example.sigmaindustry.data.remote.dto.SearchResult

sealed class DetailsEvent {

    object RemoveSideEffect : DetailsEvent()

}