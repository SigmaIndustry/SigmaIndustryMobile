package com.example.sigmaindustry.presentation.news_navigator

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.auth.profile.ProfileScreen
import com.example.sigmaindustry.presentation.auth.profile.ProfileScreenViewModel
import com.example.sigmaindustry.presentation.auth.selectAuth.SelectAuthScreen
import com.example.sigmaindustry.presentation.auth.selectAuth.SelectAuthViewModel
import com.example.sigmaindustry.presentation.cart.CartView
import com.example.sigmaindustry.presentation.cart.CartViewModel
import com.example.sigmaindustry.presentation.details.DetailsScreen
import com.example.sigmaindustry.presentation.details.DetailsViewModel
import com.example.sigmaindustry.presentation.home.HomeScreen
import com.example.sigmaindustry.presentation.home.HomeViewModel
import com.example.sigmaindustry.presentation.navgraph.Route
import com.example.sigmaindustry.presentation.news_navigator.components.BottomNavigationItem
import com.example.sigmaindustry.presentation.news_navigator.components.NewsBottomNavigation
import com.example.sigmaindustry.presentation.search.SearchScreen
import com.example.sigmaindustry.presentation.search.SearchViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator(
    viewModel: NewsNavigatorViewModel
) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Filled.Search, text = "Search"),
            BottomNavigationItem(icon = Icons.Filled.Person, text = "Profile"),
            BottomNavigationItem(icon = Icons.Filled.ShoppingCart, text = "Cart"),
        )
    }

    SideEffect {
        viewModel.onEvent(NewsNavigatorEvent.GetToken)
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.SelectAuthScreen.route -> 2
        Route.CardScreen.route -> 3
        else -> selectedItem
    }


    val isBottomBarVisible = remember(key1 = backStackState) {
        val route = backStackState?.destination?.route
         route == Route.HomeScreen.route ||
                route == Route.SearchScreen.route ||
                route == Route.BookmarkScreen.route ||
                route == Route.SelectAuthScreen.route ||
                route == Route.CardScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToProfile(
                            navController = navController,
                            viewModel = viewModel
                        )
                        3 -> navigateToTab(
                            navController = navController,
                            Route.CardScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val services = viewModel.services.collectAsLazyPagingItems()
                HomeScreen(
                    services = services,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    viewModel = viewModel,
                    navigateToDetails = { service ->
                        navigateToDetails(
                            navController = navController,
                            service = service
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    viewModel = viewModel,
                    navigateToDetails = { service ->
                        navigateToDetails(
                            navController = navController,
                            service = service
                        )
                    }
                )
            }

            composable(route = Route.SelectAuthScreen.route) {
                val viewModel: SelectAuthViewModel = hiltViewModel()
                OnBackClickStateSaver(navController = navController)
                SelectAuthScreen(viewModel = viewModel, event = viewModel::onEvent)
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Service?>("service")
                    ?.let { service ->
                        DetailsScreen(
                            service = service,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect,
                            viewModel = viewModel
                        )
                    }
            }

            composable(route = Route.CardScreen.route) {
                val viewModel: CartViewModel = hiltViewModel()
                val state = viewModel.state
                CartView(event = viewModel::onEvent, viewModel = viewModel,  state = state)
            }
            composable(route = Route.ProfileScreen.route) {
                val viewModel: ProfileScreenViewModel = hiltViewModel()
                val state = viewModel.state
                ProfileScreen(state = state, event = viewModel::onEvent) {
                    
                }
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController, route: String = Route.HomeScreen.route) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun navigateToProfile(navController: NavController, viewModel: NewsNavigatorViewModel) {
        if(viewModel.state.value.token == null){
            navController.navigate(Route.SelectAuthScreen.route)
        } else {
            navController.navigate(Route.ProfileScreen.route)
        }


//    navController.navigate(   Route.SelectAuthScreen.route) {
//        navController.graph.startDestinationRoute?.let { screen_route ->
//            popUpTo(screen_route) {
//                saveState = true
//            }
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
}


private fun navigateToDetails(navController: NavController, service: Service) {
    navController.currentBackStackEntry?.savedStateHandle?.set("service", service)
    println("Navigate to detail")
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}