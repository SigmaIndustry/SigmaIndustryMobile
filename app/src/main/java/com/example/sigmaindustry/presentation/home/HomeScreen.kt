package com.example.sigmaindustry.presentation.home
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.common.SearchBar
import com.example.sigmaindustry.presentation.common.SearchResultList


@Composable
fun HomeScreen(
    services: LazyPagingItems<Service>,
    navigateToSearch: () -> Unit,
    viewModel: HomeViewModel,
    navigateToDetails: (Service) -> Unit,
    errorHandler: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        SearchResultList(
            services = services,
            serviceUpdater = {s -> viewModel.changeServiceCategory(s)},
            onClick = navigateToDetails
        )
    }
}