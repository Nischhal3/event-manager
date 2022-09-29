package com.example.eventmanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data access object of Event database
 */
@Dao
interface UserDao {

    /**
     * @return list of user
     */
    @Query("SELECT * FROM User")
    fun getAllUser(): LiveData<List<User>>

    /**
     * @param user
     * Adds user in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    /**
     * @param userName
     * @return user via user name
     */
    @Query("SELECT * FROM user WHERE user.user_name = :userName")
    fun getUserByUserName(userName: String): LiveData<User>

    /**
     * @param userName
     * Deletes user via user name
     */
    @Query("DELETE FROM user WHERE user.user_name = :userName")
    suspend  fun deleteUse(userName: String)

    /**
     * Deletes all user in database
     */
    @Query("DELETE FROM user")
    suspend  fun deleteAll()
}