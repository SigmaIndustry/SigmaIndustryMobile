package com.example.sigmaindustry.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object CardScreen : Route(route = "cardScreen")
    object HomeScreen : Route(route = "homeScreen")
    object CreateServicesScreen : Route(route = "createServicesScreen")
    object SearchScreen : Route(route = "searchScreen")
    object BookmarkScreen : Route(route = "bookMarkScreen")
    object ProviderServicesScreen : Route(route = "providerServicesScreen")
    object LoginScreen : Route(route = "loginScreen")
    object SignUpScreen : Route(route = "signUpScreen")
    object SelectAuthScreen : Route(route = "selectAuthScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object DetailsScreenProvider : Route(route = "detailsScreenProvider")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigator")
    object AddGeoScreen : Route(route = "addGeo")
}