package com.example.eventmanager

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Event: Screen(route = "event")
    object Map: Screen(route = "map")
    object Account: Screen(route = "account")
}