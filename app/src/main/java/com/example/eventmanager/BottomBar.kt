package com.example.eventmanager


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBar(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Event: BottomBar(
        route = "event",
        title = "Event",
        icon = Icons.Default.CalendarViewMonth
    )
    object Map: BottomBar(
        route = "map",
        title = "Map",
        icon = Icons.Default.Place
    )
    object Account: BottomBar(
        route = "account",
        title = "Account",
        icon = Icons.Default.Person
    )
}
