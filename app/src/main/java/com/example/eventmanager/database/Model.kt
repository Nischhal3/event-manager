package com.example.eventmanager.database

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

@Entity(foreignKeys = [ForeignKey(
    entity = User::class,
    onDelete = CASCADE,
    parentColumns = ["user_id"],
    childColumns = ["uid"]
)])
data class Event(
    @ColumnInfo(index = true)
    val uid: Long,
    @PrimaryKey
    val event_name: String,
    val category: String,
    val city: String,
    val postalCode: String,
    val street: String,
    val country: String,
    val date: String,
    val time: String,
)

class UserAndEvent(
    @Embedded
    val user: User? = null,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "uid"
    )
    val event: List<Event>? = null
)