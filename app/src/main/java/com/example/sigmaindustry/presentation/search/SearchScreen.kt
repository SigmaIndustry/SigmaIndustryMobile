package com.example.sigmaindustry.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.common.SearchBar
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(SearchResult) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.services?.let {
            var services = it.collectAsLazyPagingItems()
            SearchResultList(
                services = services,
                onClick = navigateToDetails
            )
        }
    }
}

@Composable
fun SearchResultList(
    services: LazyPagingItems<SearchResult>,
    onClick: (SearchResult) -> Unit
) {
    LazyColumn {
        items(services.itemCount, key = services.itemKey {it.id}) { index ->
            val s = services[index]
            if (s != null) {
                ServiceCard(services = s)
            } else {
                Text(text = "Unknown error", color = Color.White)
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
        }
    }
}