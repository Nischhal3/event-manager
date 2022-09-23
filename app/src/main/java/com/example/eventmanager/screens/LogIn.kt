package com.example.eventmanager.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.eventmanager.Screen

@Composable
fun LogIn( navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
        ) {
        Text(text = "Log In Screen",
        modifier = Modifier.clickable { navController.navigate(route = Screen.Home.route) })
    }
}