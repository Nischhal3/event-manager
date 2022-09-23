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
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen",
            // modifier = Modifier.clickable { navController.popBackStack() }
            modifier = Modifier.clickable { navController.navigate(Screen.LogIn.route ) {
                popUpTo(Screen.LogIn.route){
                    inclusive = true
                }
            } }
        )

    }
}