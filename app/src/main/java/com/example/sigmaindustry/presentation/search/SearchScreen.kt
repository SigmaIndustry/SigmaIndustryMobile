package com.example.sigmaindustry.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.common.SearchBar
import com.example.sigmaindustry.presentation.common.SearchResultList
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun SearchScreen(
    state: SearchState,
    viewModel: SearchViewModel,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(Service) -> Unit
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.services?.let {
            val services = it.collectAsLazyPagingItems()
            SearchResultList(
                services = services,
                serviceUpdater = {s -> viewModel.changeServiceCategory(s)},
                onClick = navigateToDetails
            )
        }
    }
}
