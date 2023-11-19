package com.example.sigmaindustry.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
        }
    }
}


@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    services: List<Service>,
    onClick: (Service) -> Unit
) {
    if (services.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = services.size,
        ) {
            services[it].let { service ->
                ServiceCard(s = service, onClick = { onClick(service) })
            }
        }
    }

}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    services: LazyPagingItems<Service>,
    onClick: (Service) -> Unit
) {

    val handlePagingResult = handlePagingResult(services)


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = services.itemCount,
            ) {
                services[it]?.let { service ->
                    ServiceCard(s = service, onClick = { onClick(service) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(services: LazyPagingItems<Service>): Boolean {
    val loadState = services.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}