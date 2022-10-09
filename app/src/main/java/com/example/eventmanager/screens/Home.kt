package com.example.eventmanager.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmanager.R
import com.example.eventmanager.database.Event
import com.example.eventmanager.ui.theme.Background
import com.example.eventmanager.viewmodel.UserViewModel
import java.util.*
import kotlin.collections.ArrayList

@Composable

fun HomeScreen(
    userId: Long?,
    userViewModel: UserViewModel,
    navController: NavController,
) {
    var value by remember {
        mutableStateOf("...")
    }
    // Fetching list of events by userId
    val eventListByUser =
        userId?.let { userViewModel.getAllEventByUserId(it).observeAsState(listOf()) }
    val context = LocalContext.current



    Box {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-30).dp),
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = "Header Background",
            contentScale = ContentScale.FillWidth
        )
        Column() {
            Spacer(modifier = Modifier.padding(top = 36.dp))
            //Text(text = "hello $value")
            Spacer(modifier = Modifier.padding(top = 36.dp))
            Content(eventListByUser, navController)
        }
    }
}

@Composable
fun AppBar(state: MutableState<TextFieldValue>) {
    Row(
        Modifier
            .padding(16.dp)
            .height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextField(
            value = state.value,
            onValueChange = { value -> state.value = value },
            label = {
                Text(
                    text = "Search Events, Places, etc.",
                    fontSize = 12.sp,
                )
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "",
                tint = Color.White
            )
        }
        IconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun Content(
    eventListByUser: State<List<Event>>?,
    navController: NavController,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.clickable { keyboardController?.hide() }) {

        AppBar(textState)
        Spacer(modifier = Modifier.height(16.dp))
        // CategorySection()
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        ListOfEvents(
            eventListByUser = eventListByUser,
            navController = navController,
            state = textState
        )
    }
}


@Composable
fun CategorySection() {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .background((Background))
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Category", style = MaterialTheme.typography.h6)
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryButton(
                text = "Sport",
                icon = painterResource(id = R.drawable.ic_sports),
                backgroundColor = Color(0xFFF0635A)
            )
            CategoryButton(
                text = "Music",
                icon = painterResource(id = R.drawable.ic_music),
                backgroundColor = Color(0xFFF59762)
            )
            CategoryButton(
                text = "Food",
                icon = painterResource(id = R.drawable.ic_food),
                backgroundColor = Color(0xFF29D697)
            )
            CategoryButton(
                text = "Arts",
                icon = painterResource(id = R.drawable.ic_brush),
                backgroundColor = Color(0xFF46CDFB)
            )
        }
    }
}

@Composable
fun CategoryButton(
    text: String = "",
    icon: Painter,
    backgroundColor: Color
) {
    Column(
        Modifier
            .width(60.dp)
            .clickable { }
    ) {
        Box(
            Modifier
                .size(60.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(15.dp)
        ) {
            Image(painter = icon, contentDescription = "", modifier = Modifier.fillMaxSize())
        }
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ListOfEvents(
    eventListByUser: State<List<Event>>?,
    navController: NavController,
    state: MutableState<TextFieldValue>
) {

    Column(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Upcoming Events", style = MaterialTheme.typography.h6)
        }

        eventListByUser?.value?.let { it ->
            if (eventListByUser != null) {
                val searchedText = state.value.text
                if (searchedText.isEmpty()) {

                    LazyColumn(
                        modifier = Modifier
                            .height(550.dp)
                            .padding(start = 15.dp, top = 15.dp)
                    ) {
                        items(it) { item ->
                            EventCard(
                                name = item.event_name,
                                country = item.country,
                                date = item.date,
                                navController = navController
                            )
                        }
                    }

                } else {
                    val resultList = ArrayList<Event>()
                    eventListByUser.value.forEach {
                        if (it.event_name.lowercase(Locale.getDefault())
                                .contains(searchedText.lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(it)
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                EventCard(
                                    name = it.event_name,
                                    country = it.country,
                                    date = it.date,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun EventCard(
    name: String,
    country: String,
    date: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(340.dp)
            .wrapContentHeight()
            .clickable { navController.navigate("details/$name/$date") },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.event1),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = country,
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}