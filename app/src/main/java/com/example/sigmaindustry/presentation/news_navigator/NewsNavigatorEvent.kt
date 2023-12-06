package com.example.sigmaindustry.presentation.news_navigator

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NewsNavigatorEvent {
    object GetToken : NewsNavigatorEvent()
    object SaveToken: NewsNavigatorEvent()
    data class ChangeTopBarTitle(val newTitle: String) : NewsNavigatorEvent()
    data class ChangeTopBarNavIcon(val newIcon: ImageVector, val newIconDesc: String, val onClick: () -> Unit) : NewsNavigatorEvent()
    object ClearTopBarIcon : NewsNavigatorEvent()
}