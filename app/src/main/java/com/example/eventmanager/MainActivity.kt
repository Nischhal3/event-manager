package com.example.eventmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmanager.navigation.AuthNavigationScreens
import com.example.eventmanager.screens.loginScreen
import com.example.eventmanager.screens.Register
import com.example.eventmanager.ui.theme.EventManagerTheme
import com.example.eventmanager.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    companion object{
        private lateinit var userViewModel: UserViewModel
        private lateinit var userName: MutableState<String>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel(application)
        setContent {
            EventManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = AuthNavigationScreens.Login.route) {
                        composable(AuthNavigationScreens.Login.route) {
                            userName = loginScreen(navController, userViewModel)
                        }
                        composable(AuthNavigationScreens.Register.route) { Register(navController, userViewModel) }
                        composable(AuthNavigationScreens.Main.route) { MainFragment(navController, userName, userViewModel) }
                    }
                }
            }
        }
    }
}
