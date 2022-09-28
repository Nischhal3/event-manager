package com.example.eventmanager.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun Account(userName: MutableState<String>, userViewModel: UserViewModel) {
    // Converting mutable string normal string
    var userNameAsString = ""
    if(userName.value.isNotEmpty()){
        userNameAsString = userName.value
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
    }
}