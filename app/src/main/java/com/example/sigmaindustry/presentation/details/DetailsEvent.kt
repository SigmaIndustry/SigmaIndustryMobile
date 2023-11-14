package com.example.sigmaindustry.presentation.details

import com.example.sigmaindustry.data.remote.dto.SearchResult

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: SearchResult) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}