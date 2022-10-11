package com.example.eventmanager.database

import android.content.Context
import androidx.room.*
import com.example.eventmanager.converter.Converter

/**
 * Instance of the database
 */
@Database(entities = [(User::class), (Event::class), (EventImage::class)], version = 7)
@TypeConverters(Converter::class)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao
    abstract fun ImageDao(): ImageDao

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