package com.example.eventmanager

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventmanager.navigation.BottomNavigationScreens
import com.example.eventmanager.navigation.MainNavHost
import com.example.eventmanager.navigation.bottomNavigationItems
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun MainFragment(
    userName: MutableState<String>,
    userViewModel: UserViewModel,
) {
    val navController = rememberNavController()
    Scaffold(
        content = {
            MainNavHost(
                navController = navController,
                userName,
                userViewModel
            )
        },
        bottomBar = {
            BottomNav(navController, bottomNavigationItems)
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                onClick = { navController.navigate("events") },
                elevation = FloatingActionButtonDefaults.elevation(20.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "home",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
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
                icon = {
                    Icon(
                        imageVector = (screen.icon),
                        contentDescription = "icon for navigation item"
                    )
                },
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