package com.example.eventmanager.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventmanager.viewmodel.UserViewModel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EventDetails(
    navController: NavController,
    name: String?,
    date: String?,
    description: String?,
    imageIdAsString: String?,
    userViewModel: UserViewModel,
) {
    val imageList = userViewModel.getAllImage().observeAsState(listOf())
    var bitmapImage by remember {
        mutableStateOf<Bitmap?>(null)
    }
    // Converting string imageId to Long imageId
    val imageIdToLong = imageIdAsString?.toLong()

    // Fetching right bitmap image value through image id
    imageList.value.map {
        if (imageIdToLong == it.image_id) {
            bitmapImage = it.image
        }
    }


        Column(
            modifier = Modifier
                .height(710.dp)
                .verticalScroll(rememberScrollState())
        ) {
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

                })

            bitmapImage?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Header Background",
                    contentScale = ContentScale.FillWidth
                )
            }
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
                text = "$description",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
