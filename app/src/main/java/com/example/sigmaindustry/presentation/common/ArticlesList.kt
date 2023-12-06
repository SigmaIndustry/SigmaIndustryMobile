package com.example.sigmaindustry.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding2
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun SearchResultList(
    services: LazyPagingItems<Service>,
    serviceUpdater: (Service) -> Service,
    onClick: (Service) -> Unit
) {
    LazyColumn(Modifier.padding(Dimens.MediumPadding1)) {
        items(services.itemCount, key = services.itemKey {it.id}) { index ->
            val s = services[index]
            if (s != null) {
                ServiceCard(s = serviceUpdater(s), onClick = onClick)
            } else {
                Text(text = "Unknown error", color = Color.White)
            }
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))
            Divider()
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))
        }
    }
}

