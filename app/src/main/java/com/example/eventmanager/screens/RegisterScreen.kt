package com.example.eventmanager.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.eventmanager.R
import com.example.eventmanager.database.User
import com.example.eventmanager.ui.theme.Background
import com.example.eventmanager.ui.theme.MainText
import com.example.eventmanager.ui.theme.Secondary
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun Register(navController: NavController, userViewModel: UserViewModel) {
    //userViewModel.addUser(User(0, "Biswas", "KC", "biswas", "123"))
    userViewModel.deleteUsers()
    val userList = userViewModel.getAllUser().observeAsState(listOf())
    userList.value.forEach {
        Log.d("user", "${it.first_name}: ${it.user_id}")
    }
    Log.d("user", userList.value.size.toString())
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {

            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(200.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Sign Up", fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = nameValue.value,
                    onValueChange = { nameValue.value = it },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Enter name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(3.dp)
                )
                TextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email") },
                    placeholder = { Text(text = "Enter email") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(3.dp)
                )

                TextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = "passwordIcon",
                                tint = if (passwordVisibility.value) MainText else Color.Gray
                            )
                        }

                    },
                    label = { Text(text = "Password", color = MainText) },
                    placeholder = { Text(text = "Enter password") },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .fillMaxWidth(0.8f)

                )
                TextField(
                    value = confirmPasswordValue.value,
                    onValueChange = { confirmPasswordValue.value = it },
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = "passwordIcon",
                                tint = if (confirmPasswordVisibility.value) MainText else Color.Gray
                            )
                        }

                    },
                    label = { Text(text = "Confirm password", color = MainText) },
                    placeholder = { Text(text = "Confirm password") },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 10.dp,
                            end = 10.dp
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Secondary,
                        Color.White
                    ),
                    shape = MaterialTheme.shapes.medium,

                    ) {

                    Text(text = "Sign Up", color = Background)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Login Instead",
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.navigate("login") {
                                launchSingleTop = true
                            }
                        },
                    )
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}
