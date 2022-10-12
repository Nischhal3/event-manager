package com.example.eventmanager.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eventmanager.MainActivity
import com.example.eventmanager.R
import com.example.eventmanager.database.Event
import com.example.eventmanager.ui.theme.Main
import com.example.eventmanager.ui.theme.MainText
import com.example.eventmanager.viewmodel.DateAndTimeViewModel
import com.example.eventmanager.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEvent(userId: Long?, userViewModel: UserViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dateAndTimeViewModel = DateAndTimeViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current

    var name by remember {
        mutableStateOf("")
    }
    var category by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }

    var street by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var imageUri by remember {
        mutableStateOf("")
    }

    var isEnabled = true

    // Disabling button if any of these text fields are empty
    if (name.isEmpty() || category.isEmpty() || city.isEmpty() || street.isEmpty() || date.isEmpty() || time.isEmpty()) {
        isEnabled = false
    }

    // Passing imageUri to userViewModel for bitmap conversion
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri.toString()
            coroutineScope.launch {
                userViewModel.getBitmap(context, imageUri)
            }
        }

    Box {
        Image(
            modifier = Modifier
                .offset(0.dp, (-30).dp),
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Header Background",
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { keyboardController?.hide() }
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text("Add New Event")
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
            )
            Spacer(modifier = Modifier.height(55.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Dns,
                        contentDescription = ""
                    )
                },
                label = { Text(text = "Event name", color = MainText) },
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(340.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TextField(
                value = category,
                onValueChange = { category = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.SelectAll,
                        contentDescription = ""
                    )
                },
                label = { Text(text = "Category", color = MainText) },
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(340.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TextField(
                value = city,
                onValueChange = { city = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationCity,
                        contentDescription = ""
                    )
                },
                label = { Text(text = "Location", color = MainText) },
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(340.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TextField(
                value = street,
                onValueChange = { street = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Streetview,
                        contentDescription = ""
                    )
                },
                label = { Text(text = "Address", color = MainText) },
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(340.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Streetview,
                        contentDescription = ""
                    )
                },
                label = { Text(text = "Description", color = MainText) },
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(340.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Row {
                Button(onClick = {
                    launcher.launch("image/*")

                }) {
                    Text("Pick Image")
                }
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedButton(
                    onClick = {
                        // View model method call in onClick event
                        dateAndTimeViewModel.selectDateTime(context)
                        dateAndTimeViewModel.time.observe(context as MainActivity) {
                            time = it
                        }
                        dateAndTimeViewModel.date.observe(context) {
                            date = it
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MainText,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "passwordIcon",
                        modifier = Modifier.fillMaxSize(1.0f)
                    )

                }
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Button(
                onClick = {
                    if (userId != null) {
                        userViewModel.addEvent(
                            Event(
                                userId,
                                name,
                                category,
                                city,
                                street,
                                description,
                                date,
                                time,
                            )
                        )
                        userViewModel.addImage(name)
                    }
                    name = ""
                    category = ""
                    city = ""
                    street = ""
                    description = ""
                    date = ""
                    time = ""
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Main
                ),
                modifier = Modifier
                    .height(55.dp)
                    .width(340.dp),
                enabled = isEnabled,
            )
            {
                Text(text = "Add Event", color = Color.White)
            }
        }
    }
}