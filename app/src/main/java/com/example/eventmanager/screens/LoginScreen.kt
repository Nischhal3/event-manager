package com.example.eventmanager.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.eventmanager.R
import com.example.eventmanager.ui.theme.Background
import com.example.eventmanager.ui.theme.MainText
import com.example.eventmanager.ui.theme.Secondary
import com.example.eventmanager.ui.theme.orange
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun loginScreen(
    navController: NavController,
    userViewModel: UserViewModel,
): MutableState<String> {
    val userList = userViewModel.getAllUser().observeAsState(listOf())
    userList.value.forEach {
        Log.d("user", "${it.password}: ${it.user_name}")
    }
    Log.d("user", userList.value.size.toString())

    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    var isEnabled = true
    if (userName.value.isEmpty() || password.value.isEmpty()) {
        isEnabled = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            val signupText = buildAnnotatedString {
                append("Don't have an account? ")
                withStyle(SpanStyle(color = orange)) {
                    append("Sign up here!")
                }
            }
            Surface(color = Secondary, modifier = Modifier.fillMaxSize()) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(y = (-30).dp)
                ) {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fb),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_twitter),
                            contentDescription = ""
                        )

                    }
                    Text(
                        modifier = Modifier.clickable { navController.navigate("register") },
                        text = signupText,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(bottom = 40.dp))

                }
            }

            Surface(
                color = Background, modifier = Modifier
                    .height(550.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(60.dp).copy(
                    topStart = ZeroCornerSize,
                    topEnd = ZeroCornerSize
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val context = LocalContext.current
                    Image(
                        painter = painterResource(id = R.drawable.landing),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextField(
                        value = userName.value,
                        onValueChange = { userName.value = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        label = { Text(text = "Email Address", color = MainText) },
                        placeholder = { Text(text = "Enter email") },
                        modifier = Modifier.padding(vertical = 3.dp)

                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "passwordIcon"
                            )
                        },
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        label = { Text(text = "Password", color = MainText) },
                        placeholder = { Text(text = "Enter password") },
                        modifier = Modifier.padding(vertical = 3.dp)
                    )

                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.clickable { navController.navigate("register") },
                            text = "Forgot password?",
                            textAlign = TextAlign.End,
                            color = MainText
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                    Button(
                        onClick = {
                            // userList.value.map {
                            //   if (it.user_name == userName.value && it.password == password.value) {
                                    navController.navigate("main") {
                                        popUpTo("login") { inclusive = true }
                                           }
                                        //   } else {
                                        //       Toast.makeText(
                                        //          context,
                                        //            "Invalid username or password",
                                        //           Toast.LENGTH_SHORT
                                        //       ).show()
                                        //    }
                                        //  }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Secondary,
                            Color.White
                        ),
                        shape = MaterialTheme.shapes.medium,
                        // enabled = isEnabled

                    ) {
                        Text(text = "Log In")
                    }
                }
            }
        }
    }
    return  userName
}
