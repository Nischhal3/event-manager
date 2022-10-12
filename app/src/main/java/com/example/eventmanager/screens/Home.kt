package com.example.eventmanager.screens

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import com.example.eventmanager.alarm.MyAlarm
import com.example.eventmanager.database.Event
import com.example.eventmanager.ui.theme.Background
import com.example.eventmanager.ui.theme.Gray
import com.example.eventmanager.ui.theme.delete
import com.example.eventmanager.viewmodel.UserViewModel
import java.util.*

@Composable

fun HomeScreen(
    userId: Long?,
    userViewModel: UserViewModel,
    navController: NavController,
) {
    // Fetching list of events by userId
    val eventListByUser =
        userId?.let { userViewModel.getAllEventByUserId(it).observeAsState(listOf()) }

    Box {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-30).dp),
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = "Header Background",
            contentScale = ContentScale.FillWidth
        )
        Column {
            Spacer(modifier = Modifier.padding(top = 36.dp))
            //Text(text = "hello $value")
            Spacer(modifier = Modifier.padding(top = 36.dp))
            Content(eventListByUser, navController, userViewModel)
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
    userViewModel: UserViewModel,
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
            userViewModel = userViewModel,
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
    state: MutableState<TextFieldValue>,
    userViewModel: UserViewModel
) {
    val imageList = userViewModel.getAllImage().observeAsState(listOf())

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

            val searchedText = state.value.text
            if (searchedText.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .height(550.dp)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    items(it) { item ->
                        var imageId: Long? = null
                        var bitmapImage: Bitmap? = null
                        imageList.value.map { imageObj ->
                            if (imageObj.e_name == item.event_name) {
                                bitmapImage = imageObj.image
                                imageId = imageObj.image_id
                            }
                        }
                        bitmapImage?.let { bitmap ->
                            EventCard(
                                name = item.event_name,
                                country = item.city,
                                date = item.date,
                                time = item.time,
                                navController = navController,
                                userViewModel = userViewModel,
                                bitmapImage = bitmap,
                                imageId = imageId,
                            )
                        }
                    }
                }

            } else {
                val resultList = ArrayList<Event>()

                eventListByUser.value.forEach {
                    var bitmapImage: Bitmap? = null
                    var imageId: Long? = null
                    imageList.value.map { imageObj ->
                        if (imageObj.e_name == it.event_name) {
                            bitmapImage = imageObj.image
                            imageId = imageObj.image_id
                        }
                    }
                    if (it.event_name.lowercase(Locale.getDefault())
                            .contains(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(it)
                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp)
                        ) {
                            bitmapImage?.let { bitmap ->
                                EventCard(
                                    name = it.event_name,
                                    country = it.city,
                                    date = it.date,
                                    time = it.time,
                                    navController = navController,
                                    userViewModel = userViewModel,
                                    bitmapImage = bitmap,
                                    imageId = imageId
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
    time: String,
    navController: NavController,
    userViewModel: UserViewModel,
    bitmapImage: Bitmap,
    imageId: Long?,
) {
    val context = LocalContext.current
    // Converting Long imageId to String to pass it's value through navigation
    val imageIdAsString = imageId.toString()
    val splitTime = time.split(":").toMutableList()
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(340.dp)
            .wrapContentHeight()
            .clickable { navController.navigate("details/$name/$date/$imageIdAsString") },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                bitmap = bitmapImage.asImageBitmap(),
                //painter = painterResource(id = R.drawable.event1),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp)),
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
                Button(onClick = {
                    setAlarm(context, date, time)
                    if(splitTime[1].toInt() == 0){
                        Toast.makeText(
                            context,
                            "Alarm is set for $date,${splitTime[0].toInt() - 1}:${60-1}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "Alarm is set for $date,${splitTime[0].toInt()}:${splitTime[1].toInt() - 1}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text("Alarm")
                }
            }

            Spacer(modifier = Modifier.width(60.dp))
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.width(60.dp)) {
                Spacer(modifier = Modifier.height(10.dp))

                IconButton(
                    onClick = {
                        userViewModel.deleteEvent(name)
                    },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 40.dp, minHeight = 30.dp)
                        .padding(start = 10.dp)
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = delete
                    )
                }

                Text(text = "See more", color = Gray, fontSize = 14.sp)
            }

        }
    }
}

/**
 * Alarm service
 */
@SuppressLint("UnspecifiedImmutableFlag")
private fun setAlarm(context: Context, date: String, time: String) {
    val splitDate = date.split("-").toMutableList()
    val splitTime = time.split(":").toMutableList()
    val year = splitDate[2].toInt()
    val month = splitDate[1].toInt()
    val day = splitDate[0].toInt()
    val hour: Int
    val minute: Int
    if (splitTime[1].toInt() == 0) {
        minute = 60 - 1
        hour = splitTime[0].toInt() - 1
    } else {
        minute = splitTime[1].toInt() - 1
        hour = splitTime[0].toInt()
    }
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    // Setting time for alarm input
    val calendar: Calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }

    val intent = Intent(context, MyAlarm::class.java)
    val pendingIntent =
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}
