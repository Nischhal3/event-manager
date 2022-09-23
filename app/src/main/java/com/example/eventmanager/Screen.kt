package com.example.eventmanager

sealed class Screen(val route: String) {
    object LogIn: Screen(route = "login")
    object Home: Screen(route = "home_screen")
}