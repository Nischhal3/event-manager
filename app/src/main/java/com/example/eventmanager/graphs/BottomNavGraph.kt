package com.example.eventmanager

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventmanager.screens.Home
import com.example.eventmanager.screens.Event
import com.example.eventmanager.screens.Map
import com.example.eventmanager.screens.Account

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route
    ){
        composable(
            route = BottomBar.Home.route
        ){
            Home()
        }
        composable(
            route = BottomBar.Event.route
        ){
            Event()
        }
        composable(
            route = BottomBar.Map.route
        ){
            Map()
        }
        composable(
            route = BottomBar.Account.route
        ){
            Account()
        }
    }
}