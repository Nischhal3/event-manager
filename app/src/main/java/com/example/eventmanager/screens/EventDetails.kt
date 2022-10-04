package com.example.eventmanager.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EventDetails(
    navController: NavController,
    name:String?,
    date:String?,
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Event Details")
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

        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(340.dp)
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = com.example.eventmanager.R.drawable.event1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = "$name",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface,
                    )
                    Text(
                        text = "$date",
                        style = MaterialTheme.typography.body2,
                    )


                }
            }
        }

    }
}