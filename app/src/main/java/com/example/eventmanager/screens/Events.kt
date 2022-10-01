package com.example.eventmanager.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmanager.MainActivity
import com.example.eventmanager.database.Event
import com.example.eventmanager.ui.theme.MainText
import com.example.eventmanager.ui.theme.Purple500
import com.example.eventmanager.viewmodel.DateAndTimeViewModel
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun Events(userId: Long?, userViewModel: UserViewModel) {
    // val eventList = userViewModel.getAllEvent().observeAsState(listOf())
    var name by remember {
        mutableStateOf("")
    }
    var category by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var postalCode by remember {
        mutableStateOf("")
    }

    var street by remember {
        mutableStateOf("")
    }
    var country by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var isEnabled = true

    // Disabling button if any of these text fields are empty
    if (name.isEmpty() || category.isEmpty() || city.isEmpty() || postalCode.isEmpty() || street.isEmpty() || country.isEmpty() || date.isEmpty() || time.isEmpty()) {
        isEnabled = false
    }
    val context = LocalContext.current
    val dateAndTimeViewModel = DateAndTimeViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "Name", color = MainText) },
            placeholder = { Text(text = "City") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        TextField(
            value = category,
            onValueChange = { category = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "Category", color = MainText) },
            placeholder = { Text(text = "City") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        TextField(
            value = city,
            onValueChange = { city = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "City", color = MainText) },
            placeholder = { Text(text = "City") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = street,
            onValueChange = { street = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "Street", color = MainText) },
            placeholder = { Text(text = "Street") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = postalCode,
            onValueChange = { postalCode = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "PostalCode", color = MainText) },
            placeholder = { Text(text = "PostalCode") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = country,
            onValueChange = { country = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            label = { Text(text = "Country", color = MainText) },
            placeholder = { Text(text = "Country") },
            modifier = Modifier.padding(vertical = 3.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        // TODO
/*        Button(onClick = {
            locationViewModel.city.observe(context as MainActivity) {
                city = it
            }
            locationViewModel.postalCode.observe(context) {
                postalCode = it
            }
            locationViewModel.street.observe(context) {
                street = it
            }
            locationViewModel.countryName.observe(context) {
                country = it
            }
        }) {
            Text("Use current Location")
        }*/
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            if (userId != null) {
                userViewModel.addEvent(
                    Event(
                        userId,
                        name,
                        category,
                        city,
                        street,
                        postalCode,
                        country,
                        date,
                        time
                    )
                )
            }
            name = ""
            category = ""
            city = ""
            street = ""
            postalCode = ""
            country = ""
            date = ""
            time = ""
        }, enabled = isEnabled)
        {
            Text("Add Event")

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calender Date & Time Picker",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Add Date picker icon or image here : UI
            TextButton(
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
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Purple500)
                    .padding(5.dp)
            ) {
                Text(text = "Select Date", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "$date, $time")
        }
    }

}