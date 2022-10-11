package com.example.eventmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data access object of Event database
 */
@Dao
interface EventDao {

    /**
     * @return list of event
     */
    @Query("SELECT * FROM Event")
    fun getAllEvent(): LiveData<List<Event>>

    /**
     * @param event
     * Adds event to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(event: Event): Long

    /**
     * @param event
     * Updates event in the detabase
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: Event)

    /**
     * @param eventName
     * Deletes event from the database
     */
    @Query("DELETE FROM event WHERE event.event_name= :eventName")
    suspend  fun deleteUse(eventName: String)
}