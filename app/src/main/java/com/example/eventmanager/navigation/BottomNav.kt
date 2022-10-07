package com.example.eventmanager.navigation


import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    userViewModel: UserViewModel
) {
    var userId: Long? = null
    val userList = userViewModel.getAllUser().observeAsState(listOf())
    userList.value.forEach {
        if(it.user_name == userName.value){
            userId = it.user_id
        }
    }
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) { HomeScreen(userId, userViewModel, navController) }
        composable(BottomNavigationScreens.Events.route) { Events(userId,userViewModel) }
        composable(BottomNavigationScreens.Account.route) { Account(userName, userViewModel, navController) }
        composable("event") { EventList(navController ) }
        composable("details" + "/{name}" + "/{date}") {navBackStack ->
            val eventName = navBackStack.arguments?.getString("name")
            val date = navBackStack.arguments?.getString("date")

            EventDetails(navController = navController, name = eventName, date = date)
        }

    }
}