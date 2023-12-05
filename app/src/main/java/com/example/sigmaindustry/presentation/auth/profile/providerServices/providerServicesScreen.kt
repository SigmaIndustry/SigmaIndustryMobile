package com.example.sigmaindustry.presentation.auth.profile.providerServices

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.data.remote.dto.ServiceResponse
import com.example.sigmaindustry.data.remote.dto.User
import com.example.sigmaindustry.presentation.auth.profile.ProfileScreenEvent
import com.example.sigmaindustry.presentation.common.SearchResultList
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KSuspendFunction1
//
//@Composable
//fun providerServicesScreen(
//   // onUpdateRequest: () -> Unit,
//   // token: String?,
//    //user: User,
//   // event: KSuspendFunction1<ProfileScreenEvent, Unit>,
//   // logOut: () -> Unit
//    services: Flow<PagingData<Service>>
//) {
//    services?.let {
//        var services = it.collectAsLazyPagingItems()
//        SearchResultList(
//            services = services,
//            serviceUpdater = { s -> viewModel.changeServiceCategory(s) },
//            onClick = navigateToDetails
//        )
//    }
//}