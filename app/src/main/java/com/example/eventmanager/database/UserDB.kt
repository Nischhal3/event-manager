package com.example.eventmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Instance of the User and Event database
 */
@Database(entities = [(User::class), (Event::class)], version = 2)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao

    companion object {
        private var sInstance: UserDB? = null

        @Synchronized
        fun get(context: Context): UserDB {
            if (sInstance == null) {
                sInstance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java, "user.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }
    }
}