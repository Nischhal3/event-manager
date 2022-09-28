package com.example.eventmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventmanager.navigation.BottomNavigationScreens
import com.example.eventmanager.navigation.MainNavHost
import com.example.eventmanager.navigation.bottomNavigationItems
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun MainFragment(
    authNavController: NavController,
    userName: MutableState<String>,
    userViewModel: UserViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TitleBar(navController, bottomNavigationItems)
        },
        content = { MainNavHost(authController = authNavController, navController = navController, userName, userViewModel) },
        bottomBar = {
            BottomNav(navController, bottomNavigationItems)
        }
    )
}

@Composable
fun TitleBar(navController: NavHostController, items: List<BottomNavigationScreens>) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    if (currentRoute == screen.route) {
                        Text(text = screen.label)
                    }
                }
            }
        }
    )
}

@Composable
fun BottomNav(navController: NavHostController, items: List<BottomNavigationScreens>) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->

            BottomNavigationItem(
                selected = currentRoute == screen.route,
                icon = { Icon(imageVector = (screen.icon), contentDescription = "icon for navigation item") },
                label = { Text(text = screen.label) },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}