package com.example.eventmanager.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.eventmanager.R
import com.example.eventmanager.ui.theme.Background
import com.example.eventmanager.ui.theme.MainText
import com.example.eventmanager.ui.theme.Secondary
import com.example.eventmanager.ui.theme.orange

@Composable
fun LoginContent(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
) {
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
                        Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "")
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "")
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_twitter),
                            contentDescription = ""
                        )

                    }
                    Text(
                        modifier = Modifier.clickable { onSignUpClick() },
                        text = signupText,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(bottom = 40.dp))

                }
            }

            // Main Card
            val emailState = remember { mutableStateOf(TextFieldValue("")) }
            val passState = remember { mutableStateOf(TextFieldValue("")) }
            Surface(
                color = Background, modifier = Modifier
                    .height(600.dp)
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

                    Image(
                        painter = painterResource(id = R.drawable.landing),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
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
                        value = passState.value,
                        onValueChange = { passState.value = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "passwordIcon"
                            )
                        },
                        label = { Text(text = "Password", color = MainText) },
                        placeholder = { Text(text = "Enter password") },
                        modifier = Modifier.padding(vertical = 3.dp)
                    )

                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.clickable { onForgotClick() },
                            text = "Forgot password?",
                            textAlign = TextAlign.End,
                            color = MainText
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Secondary,
                            Color.White
                        ),
                        shape = MaterialTheme.shapes.medium,

                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 10.dp,
                            end = 10.dp
                        )

                    ) {
                        Text(text = "Log In")
                    }
                    Text(
                        modifier = Modifier.clickable { onSignUpClick() },
                        text = "Sign Up",
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        modifier = Modifier.clickable { onForgotClick() },
                        text = "Forgot Password",
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
