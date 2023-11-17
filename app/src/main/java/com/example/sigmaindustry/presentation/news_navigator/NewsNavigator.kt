package com.example.sigmaindustry.presentation.news_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.sigmaindustry.presentation.auth.signIn.LoginScreen
import com.example.sigmaindustry.presentation.auth.signIn.LoginViewModel
import com.example.sigmaindustry.presentation.auth.signUp.SignUpScreen
import com.example.sigmaindustry.presentation.auth.signUp.SignUpViewModel
import com.example.sigmaindustry.presentation.details.DetailsScreen
import com.example.sigmaindustry.presentation.details.DetailsViewModel
import com.example.sigmaindustry.presentation.home.HomeScreen
import com.example.sigmaindustry.presentation.home.HomeViewModel
import com.example.sigmaindustry.presentation.navgraph.Route
import com.example.sigmaindustry.presentation.news_navigator.components.BottomNavigationItem
import com.example.sigmaindustry.presentation.news_navigator.components.NewsBottomNavigation
import com.example.sigmaindustry.presentation.search.SearchScreen
import com.example.sigmaindustry.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator(
) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_launcher_background, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_launcher_background, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_launcher_background, text = "Profile"),
        )
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
        else -> selectedItem
    }


    val isBottomBarVisible = remember(key1 = backStackState) {
        val route = backStackState?.destination?.route
         route == Route.HomeScreen.route ||
                route == Route.SearchScreen.route ||
                route == Route.BookmarkScreen.route ||
                route == Route.SelectAuthScreen.route
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

                        2 -> navigateToTab(
                            navController = navController,
                            Route.ProfileScreen.route
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
                    navigateToDetails = { service ->
                        navigateToDetails(
                            navController = navController,
                            service = service
                        )
                    }
                )
            }

            composable(route = Route.LoginScreen.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController,
                    route = Route.SelectAuthScreen.route)
                navController.currentBackStackEntry?.savedStateHandle?.set("token", "")
                LoginScreen(
                    state = state,
                    event = viewModel::onEvent,
                )
            }

            composable(route = Route.SignUpScreen.route) {
                val viewModel: SignUpViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController,
                    route = Route.SelectAuthScreen.route)
                SignUpScreen(
                    state = state,
                    event = viewModel::onEvent
                )
            }

            composable(route = Route.SelectAuthScreen.route) {
                OnBackClickStateSaver(navController = navController)
                val viewModel: SelectAuthViewModel = hiltViewModel()
                SelectAuthScreen(viewModel = viewModel, event = viewModel::onEvent, navController = navController)
            }

            composable(route = Route.ProfileScreen.route) {
                val viewModel: ProfileScreenViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                ProfileScreen(
                    viewModel = viewModel,
                    state = state,
                    navigateToSelectAuth = {navigateToSelectAuth(navController = navController)},
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Service?>("service")
                    ?.let { service ->
                        DetailsScreen(
                            service = service,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
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


private fun navigateToDetails(navController: NavController, service: Service) {
    navController.currentBackStackEntry?.savedStateHandle?.set("service", service)
    println("Navigate to detail")
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}
private fun navigateToLogin(navController: NavController) {
}
private fun navigateToSelectAuth(navController: NavController) {
    println("Navigate to selectAuth")
    navController.navigate(
        route = Route.SelectAuthScreen.route
    )
}
private fun navigateToSignUp(navController: NavController) {
    println("Navigate to signUp")
    navController.navigate(
        route = Route.SignUpScreen.route
    )
}