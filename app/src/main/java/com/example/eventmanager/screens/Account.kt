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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.rememberImagePainter
import com.example.eventmanager.MainActivity
import com.example.eventmanager.R
import com.example.eventmanager.navigation.AuthNavigationScreens
import com.example.eventmanager.navigation.BottomNavigationScreens
import com.example.eventmanager.ui.theme.*
import com.example.eventmanager.viewmodel.UserViewModel
import java.time.format.TextStyle

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
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancel",
                modifier = Modifier.clickable { notification.value = "Cancelled" }, color = MainText
            )
            Text(
                text = "Save",
                modifier = Modifier.clickable { notification.value = "Profile updated" },
                color = MainText
            )
        }

        ProfileImage()
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Name", modifier = Modifier.width(100.dp))
            Text(text = "${user.value?.first_name}", modifier = Modifier.padding(start = 15.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Username", modifier = Modifier.width(100.dp))
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

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

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
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
        Text(text = "Change profile picture", color = Gray)
    }
}