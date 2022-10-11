package com.example.eventmanager.navigation


import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventmanager.screens.*

import com.example.eventmanager.viewmodel.UserViewModel


sealed class BottomNavigationScreens(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavigationScreens("home", "Home", Icons.Default.Home)
    object Events : BottomNavigationScreens("events", "Add Event", Icons.Default.CalendarToday)
    object Account : BottomNavigationScreens("account", "Account", Icons.Default.Person)
}

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Account,
)

@Composable
fun MainNavHost(
    authController: NavController,
    navController: NavHostController,
    userName: MutableState<String>,
    userViewModel: UserViewModel,
) {
    var userId: Long? = null
    val userList = userViewModel.getAllUser().observeAsState(listOf())
    userList.value.forEach {
        if (it.user_name == userName.value) {
            userId = it.user_id
        }
    }
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen(
                userId,
                userViewModel,
                navController
            )
        }
        composable(BottomNavigationScreens.Events.route) { AddEvent(userId, userViewModel) }
        composable(BottomNavigationScreens.Account.route) {
            Account(
                userName,
                userViewModel,
                navController
            )
        }
        composable("details" + "/{name}" + "/{date}" + "/{imageIdAsString}") { navBackStack ->
            val eventName = navBackStack.arguments?.getString("name")
            val date = navBackStack.arguments?.getString("date")
            val imageIdAsString = navBackStack.arguments?.getString("imageIdAsString")
            
            EventDetails(
                navController = navController,
                name = eventName,
                date = date,
                imageIdAsString = imageIdAsString,
                userViewModel = userViewModel,
            )
        }
    }
}