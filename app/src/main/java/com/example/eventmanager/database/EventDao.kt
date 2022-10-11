package com.example.eventmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data access object of Event database
 */
@Dao
interface EventDao {
    /**
     * @param userId
     * @return list of event by userId
     */
    @Query("SELECT * FROM Event  WHERE uid = :userId ")
    fun getAllEventByUserId(userId: Long): LiveData<List<Event>>

    /**
     * @param event
     * Adds event to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(event: Event): Long

    /**
     * @param event
     * Updates event in the database
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: Event)

    /**
     * @param eventName
     * Deletes event from the database
     */
    @Query("DELETE FROM event WHERE event.event_name= :eventName")
    suspend  fun deleteEvent(eventName: String)

}

@Dao
interface ImageDao{
    /**
     * @param eventImage
     * Adding event image to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImage(eventImage: EventImage): Long

    /**
     * Fetching  all events from event image database
     */
    @Query("SELECT * FROM EventImage")
    fun getImageByEventName(): LiveData<List<EventImage>>
}