package com.example.eventmanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUser(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    @Query("SELECT * FROM user WHERE user.user_name = :userName")
    fun getUserByUserName(userName: String): LiveData<User>

    @Query("DELETE FROM user")
    suspend  fun deleteAll()
}