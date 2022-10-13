@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.eventmanager.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.eventmanager.MainActivity
import com.example.eventmanager.R
import com.example.eventmanager.ui.theme.*
import com.example.eventmanager.viewmodel.UserViewModel

@Composable
fun Account(
    userName: MutableState<String>,
    userViewModel: UserViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    // Converting mutable string to normal string
    var userNameAsString = ""
    if (userName.value.isNotEmpty()) {
        userNameAsString = userName.value
    }
    val isLoggedOut = remember {
        mutableStateOf(false)
    }

    val user = userViewModel.getUserByUserName(userNameAsString).observeAsState()
    Log.d("user", "user from Account ${user.value?.first_name} ")
    val notification = rememberSaveable { mutableStateOf("") }
    if (notification.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Profile")
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
        )
        ProfileImage()
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-10).dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Your details", color = Gray, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "First Name:", modifier = Modifier.width(100.dp))
            Text(text = "${user.value?.first_name}", modifier = Modifier.padding(start = 15.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Last Name:", modifier = Modifier.width(100.dp))
            Text(text = "${user.value?.last_name}", modifier = Modifier.padding(start = 15.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Username:", modifier = Modifier.width(100.dp))
            Text(text = userNameAsString, modifier = Modifier.padding(start = 15.dp))

        }

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isLoggedOut.value = true
            Log.d("user", "Clicked $isLoggedOut")
        }) {
            Text("Log out")
        }
        OutlinedButton(
            border = BorderStroke(1.dp, red),
            onClick = {
                userViewModel.deleteUser(userNameAsString)
                isLoggedOut.value = true
                Log.d("user", "Clicked $isLoggedOut")
            }) {
            Text("Delete Account", color = red)
        }
    }
    if (isLoggedOut.value) {
        Log.d("user", "Clicked ${isLoggedOut.value}")
        context.startActivity(Intent(context, MainActivity::class.java))
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileImage() {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }
}