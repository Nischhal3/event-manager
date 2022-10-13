@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.eventmanager.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.example.eventmanager.R
import com.example.eventmanager.database.User
import com.example.eventmanager.ui.theme.*
import com.example.eventmanager.viewmodel.UserViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Register(navController: NavController, userViewModel: UserViewModel) {
    //userViewModel.deleteUsers()
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    var isEnabled = true
    val keyboardController = LocalSoftwareKeyboardController.current

    if (firstName.value.isEmpty() || lastName.value.isEmpty() || userName.value.isEmpty()
        || confirmPassword.value.isEmpty() || password.value.isEmpty()) {
        isEnabled = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        val loginText = buildAnnotatedString {
            append("Already have an account? ")
            withStyle(SpanStyle(color = MainText, textDecoration = TextDecoration.Underline)) {
                append("Log In")
            }
        }
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
                .padding(10.dp)
                .clickable {keyboardController?.hide()}
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Sign Up", fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = firstName.value,
                    onValueChange = { firstName.value = it },
                    label = { Text(text = "First Name") },
                    placeholder = { Text(text = "Enter name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(3.dp)
                )
                TextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    label = { Text(text = "Last Name") },
                    placeholder = { Text(text = "Last Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(3.dp)
                )
                TextField(
                    value = userName.value,
                    onValueChange = { userName.value = it },
                    label = { Text(text = "User Name") },
                    placeholder = { Text(text = "User Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(3.dp)
                )
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
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
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
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
                Spacer(modifier = Modifier.padding(2.dp))
                if (password.value != confirmPassword.value) {
                    isEnabled = false
                    Text("Wrong password")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
//                        navController.navigate("main") {
//                            popUpTo("login") { inclusive = true }
//                        }
                        navController.navigate("login") {
                            launchSingleTop = true
                        }
                        userViewModel.addUser(
                            User(0, firstName.value, lastName.value, userName.value, password.value))
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
                    enabled = isEnabled
                ) {

                    Text(text = "Sign Up", color = Background)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = loginText,
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
