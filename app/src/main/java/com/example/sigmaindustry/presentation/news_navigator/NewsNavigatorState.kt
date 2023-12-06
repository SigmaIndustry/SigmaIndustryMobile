package com.example.sigmaindustry.presentation.news_navigator

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class NewsNavigatorState (
    var token: String? = null,
    val topBarTitle: String = "Sigma Industry",
    val topBarNavIcon: ImageVector? = null,
    val topBarIconDesc: String? = null,
    val onClick: () -> Unit = {}
)