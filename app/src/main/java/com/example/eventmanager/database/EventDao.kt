package com.example.eventmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDao {

    @Query("SELECT * FROM Event")
    fun getAllEvent(): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(event: Event): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: Event)
}