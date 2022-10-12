package com.example.eventmanager.database

import android.graphics.Bitmap
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * Model of User and Event to be created in data base
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val user_id: Long,
    // @PrimaryKey
    val first_name: String,
    val last_name: String,
    val user_name: String,
    val password: String,
)

/**
 * Model of Event data base
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        onDelete = CASCADE,
        parentColumns = ["user_id"],
        childColumns = ["uid"]
    )]
)
data class Event(
    @ColumnInfo(index = true)
    val uid: Long,
    @PrimaryKey
    val event_name: String,
    val category: String,
    val city: String,
    val street: String,
    val description: String,
    val date: String,
    val time: String,
)

/**
 * Model of Image data base
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = Event::class,
        onDelete = CASCADE,
        parentColumns = ["event_name"],
        childColumns = ["e_name"]
    )]
)
data class EventImage(
    @PrimaryKey(autoGenerate = true)
    val image_id: Long,
    val e_name: String,
    val image: Bitmap
)

/**
 * One to one relation between User and Event
 * User is parent table and Event is child table
 * Whenever user is removed from db, event related to that user is also removed from db
 */
class UserAndEvent(
    @Embedded
    val user: User? = null,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "uid"
    )
    val event: List<Event>? = null
)

/**
 * One to one relation between Event And Image
 * Event is parent table and Image is child table
 * Whenever Event is removed from db, Image related to that user is also removed from db
 */
class EventAndImage(
    @Embedded
    val event: Event? = null,
    @Relation(
        parentColumn = "event_name",
        entityColumn = "e_name"
    )
    val eventImage: EventImage? = null
)