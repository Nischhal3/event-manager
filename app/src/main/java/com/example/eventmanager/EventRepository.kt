package com.example.eventmanager

import androidx.compose.ui.res.painterResource
import com.example.eventmanager.viewmodel.Event

class EventRepository {
    fun getAllData(): List<Event> {
        return listOf(
            Event(
                id = 0,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 1,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 2,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 3,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 4,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 5,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 6,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 7,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 8,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
            Event(
                id = 9,
                image = R.drawable.event1,
                date = "20.09.2022",
                name = "Event 1",
                location = "Helsinki",
            ),
        )
    }
}
