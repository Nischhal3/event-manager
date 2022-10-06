package com.example.eventmanager.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventmanager.viewmodel.Event

@Composable
fun EventList(navController: NavController) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Android Toolbar Bar sample")
            },
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Settings, null)
                }
            })

        Text("Hello World")

    }
}
/*@Composable
fun EventList2() {
    val eventRep = EventRepository()
    val getAllData = eventRep.getAllData()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text("Event list") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "\uD83C\uDF3F  Plants in Cosmetics",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
            items(items = getAllData) { plant ->
                PlantCard(plant.name, plant.date, plant.image)
            }
        }
    }
}*/
/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventList1() {
    val categories = listOf("Sports", "Music", "Food", "Art")
    val eventRep = EventRepository()
    val getAllData = eventRep.getAllData()

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
    ) {
        categories.forEach { section ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(12.dp),
                    text = "Section $section"
                )
            }
            items(10){
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Item $it from the section $section"
                )
            }
        //items(items = getAllData) { event ->
            //EventCard(event = event)
        //}
    }
}
*/


@Composable
fun EventCard(event: Event) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "${event.date}",
            color = Color.Black,
            fontSize = typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = event.name,
            color = Color.Black,
            fontSize = typography.h5.fontSize,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = event.location,
            color = Color.Black,
            fontSize = typography.h5.fontSize,
            fontWeight = FontWeight.Normal
        )
    }
}
@Composable
fun PlantCard(name: String, description: String, image: Int) {
    Card(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}