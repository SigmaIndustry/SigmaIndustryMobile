package com.example.sigmaindustry.presentation.news_navigator

sealed class NewsNavigatorEvent {
    object GetToken : NewsNavigatorEvent()
    object SaveToken: NewsNavigatorEvent()
}