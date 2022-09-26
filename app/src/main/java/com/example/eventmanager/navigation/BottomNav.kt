package com.example.eventmanager.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventmanager.*


sealed class BottomNavigationScreens(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavigationScreens("home", "Home", Icons.Default.Home)
    object Events : BottomNavigationScreens("events", "Events", Icons.Default.CalendarToday)
    object Map : BottomNavigationScreens("map", "Map", Icons.Default.Place)
    object Account : BottomNavigationScreens("account", "Account", Icons.Default.Person)
}

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Events,
    BottomNavigationScreens.Map,
    BottomNavigationScreens.Account,
)

@Composable
fun MainNavHost(authController: NavController, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) { ItemOneScreen() }
        composable(BottomNavigationScreens.Events.route) { ItemTwoScreen() }
        composable(BottomNavigationScreens.Map.route) { ItemThreeScreen() }
        composable(BottomNavigationScreens.Account.route) { ItemFourScreen() }
    }
}