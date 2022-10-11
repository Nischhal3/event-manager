package com.example.eventmanager.viewmodel

import androidx.compose.ui.graphics.vector.ImageVector

data class Event(
    val id: Int,
    val image: Int,
    val date: String,
    val name: String,
    val location: String,
)
