package com.example.eventmanager.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EventDetails(
    navController: NavController,
    name: String?,
    date: String?,
) {
    Box {


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

            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = com.example.eventmanager.R.drawable.home_bg),
                contentDescription = "Header Background",
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$name",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center
                )
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(340.dp)
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.surface,

                    ) {

                    Box(
                        Modifier.padding(12.dp),
                    ) {
                        Column() {
                            Row() {
                                Image(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(50.dp),
                                    painter = painterResource(id = com.example.eventmanager.R.drawable.date),
                                    contentDescription = "Date"
                                )
                                Text(
                                    modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                                    text = "$name",
                                    style = MaterialTheme.typography.h6,
                                    color = MaterialTheme.colors.onSurface,
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Row() {
                                Image(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(50.dp),
                                    painter = painterResource(id = com.example.eventmanager.R.drawable.location),
                                    contentDescription = "Location"
                                )
                                Text(
                                    modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                                    text = "$date",
                                    style = MaterialTheme.typography.h6,
                                    color = MaterialTheme.colors.onSurface,
                                )
                            }
                        }


                    }

                }
            }
            Text(
                text = "About Event",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$name",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}