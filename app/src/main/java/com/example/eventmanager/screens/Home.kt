@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.eventmanager.screens

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmanager.R
import com.example.eventmanager.alarm.MyAlarm
import com.example.eventmanager.database.Event
import com.example.eventmanager.ui.theme.*
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
        Spacer(modifier = Modifier.height(48.dp))
        ListOfEvents(
            eventListByUser = eventListByUser,
            userViewModel = userViewModel,
            navController = navController,
            state = textState
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
                                location = item.street,
                                date = item.date,
                                time = item.time,
                                description = item.description,
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
                                    location = it.street,
                                    date = it.date,
                                    time = it.time,
                                    description = it.description,
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
    location: String,
    date: String,
    time: String,
    description: String,
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
            .clickable { navController.navigate("details/$name/$date/$description/$imageIdAsString") },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                bitmap = bitmapImage.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit,
            )
            Column (Modifier.width(120.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.body2,
                )

            }

            Spacer(modifier = Modifier.width(20.dp))
            Column( horizontalAlignment = Alignment.End) {

                IconButton(
                    onClick = {
                        userViewModel.deleteEvent(name)
                    },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 40.dp, minHeight = 30.dp)
                        .padding(start = 30.dp, top = 35.dp)
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = delete
                    )
                }

                OutlinedButton(
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier
                        .wrapContentWidth()
                        .defaultMinSize( minHeight = 30.dp)
                        .padding(5.dp)
                        .offset(x = 10.dp, y = 5.dp),
                    onClick = {
                        setAlarm(context, date, time)
                        if (splitTime[1].toInt() == 0) {
                            Toast.makeText(
                                context,
                                "Alarm is set for $date,${splitTime[0].toInt() - 1}:${60 - 1}",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Alarm is set for $date,${splitTime[0].toInt()}:${splitTime[1].toInt() - 1}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {

                    Text("Remind me", fontSize = 12.sp, color = MainText)
                }
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
