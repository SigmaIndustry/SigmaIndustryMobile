package com.example.sigmaindustry.presentation.details

sealed class DetailsEvent {

    object ShowDialog : DetailsEvent()
    object HideDialog: DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
    object Load: DetailsEvent()
    data class SendRate(val serviceId: Int, val rating: Float, val feedback: String) : DetailsEvent()
    data class SendOrder(val serviceId: Int, val message: String) : DetailsEvent()
}