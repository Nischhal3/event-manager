package com.example.eventmanager.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Event(navController: NavController) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Android Toolbar Bar sample")
            },
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Settings, null)
                }
            })

        Text("Hello World")

    }
}