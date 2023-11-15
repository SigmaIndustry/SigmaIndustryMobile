package com.example.sigmaindustry.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding2
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    services: List<SearchResult>,
    onClick: (SearchResult) -> Unit
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
                ServiceCard(services = service, onClick = { onClick(service) })
            }
        }
    }

}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    services: LazyPagingItems<SearchResult>,
    onClick: (SearchResult) -> Unit
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
                    ServiceCard(services = service, onClick = { onClick(service) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(services: LazyPagingItems<SearchResult>): Boolean {
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