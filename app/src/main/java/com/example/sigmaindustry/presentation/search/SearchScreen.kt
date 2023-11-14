package com.example.sigmaindustry.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.common.SearchBar

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
//        Spacer(modifier = Modifier.height(MediumPadding1))
//        state.articles?.let {
//            val articles = it.collectAsLazyPagingItems()
//            ArticlesList(
//                services = articles,
//                onClick = navigateToDetails
//            )
//        }
    }
}