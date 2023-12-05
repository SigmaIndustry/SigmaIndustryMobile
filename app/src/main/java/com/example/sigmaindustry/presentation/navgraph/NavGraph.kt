package com.example.sigmaindustry.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.sigmaindustry.presentation.news_navigator.NewsNavigator
import com.example.sigmaindustry.presentation.news_navigator.NewsNavigatorViewModel


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    val newsNavigatorViewModel: NewsNavigatorViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator(newsNavigatorViewModel)
            }
        }
    }
}