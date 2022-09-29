package com.example.eventmanager.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventmanager.MainActivity
import com.example.eventmanager.navigation.AuthNavigationScreens
import com.example.eventmanager.navigation.BottomNavigationScreens
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun Account(
    userName: MutableState<String>,
    userViewModel: UserViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    // Converting mutable string to normal string
    var userNameAsString = ""
    if (userName.value.isNotEmpty()) {
        userNameAsString = userName.value
    }
    val isLoggedOut = remember {
        mutableStateOf(false)
    }

    val user = userViewModel.getUserByUserName(userNameAsString).observeAsState()
    Log.d("user", "user from Account ${user.value?.first_name} ")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Account")
        Button(onClick = {
            isLoggedOut.value = true
            Log.d("user", "Clicked $isLoggedOut")
        }) {
            Text("Log out")
        }
    }
    if (isLoggedOut.value) {
        Log.d("user", "Clicked ${isLoggedOut.value}")
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}