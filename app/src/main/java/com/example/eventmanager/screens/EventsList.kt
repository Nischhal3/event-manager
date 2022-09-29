package com.example.eventmanager.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.eventmanager.EventRepository
import com.example.eventmanager.viewmodel.Event

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventList() {
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
}}
