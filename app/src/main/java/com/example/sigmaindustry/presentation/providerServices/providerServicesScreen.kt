package com.example.sigmaindustry.presentation.providerServices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun ProviderServicesScreen(
    state: State<ProviderServicesState>,
    viewModel: ProviderServicesViewModel,
    event:(ProviderServicesEvent) -> Unit,
    navigateToDetails:(Service) -> Unit
) {
LaunchedEffect(key1 = null){
    event(ProviderServicesEvent.GetServices)
}
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
    ) {

        Text(text = "My services",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        LazyColumn() {
            state.value.services?.entries?.let { list ->
                items(list.size) {
                    val ser = state.value.services!!.entries[it]
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                    ServiceCard(s = viewModel.changeServiceCategory(ser), onClick =
                         navigateToDetails
                    )
                }
            }
        }
    }
}

