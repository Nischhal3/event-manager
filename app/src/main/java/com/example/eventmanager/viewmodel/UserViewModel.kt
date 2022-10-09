package com.example.eventmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.eventmanager.database.Event
import com.example.eventmanager.database.User
import com.example.eventmanager.database.UserDB

import kotlinx.coroutines.launch

/**
 * Provides data to the UI
 * Data base can be accessed via view model only
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDB = UserDB.get(application)

    // private val _username: MutableLiveData<String> = MutableLiveData()
    // val userName:  LiveData<String> = _username

    /**
     * @return List of User
     */
    fun getAllUser(): LiveData<List<User>> =
        userDB.userDao().getAllUser()


    /**
     * @param userName
     * @return user via userName
     */
    fun getUserByUserName(userName: String): LiveData<User> =
        userDB.userDao().getUserByUserName(userName)

    /**
     * @param user
     * Adds user to the database
     */
    fun addUser(user: User) {
        viewModelScope.launch {
            /*getAllUser().value?.forEach {
                if (it.user_name != user.user_name) {
                    userDB.userDao().addUser(user)
                } else {
                    Log.d("user", "User Exists")
                }
            }*/
            userDB.userDao().addUser(user)
        }
    }

    fun deleteUser(userName: String){
        viewModelScope.launch {

        }
    }

    /**
     * Deletes all user in database
     */
    fun deleteAllUsers() {
        viewModelScope.launch {
            userDB.userDao().deleteAll()
        }
    }

    /**
     * @return List of Events
     */
    fun getAllEvent(): LiveData<List<Event>> =
        userDB.eventDao().getAllEvent()

    /**
     * @return List of Events
     */
    fun getAllEventByUserId(userID: Long): LiveData<List<Event>> =
        userDB.eventDao().getAllEventByUserId(userID)

    /**
     * @param event
     * Adds event to the database
     */
    fun addEvent(event: Event) {
        viewModelScope.launch {
            userDB.eventDao().addEvent(event)
        }
    }

    /**
     * @param event
     * Updates event in the database
     */
    fun deleteEvent(eventName: String) {
        viewModelScope.launch {
            userDB.eventDao().deleteEvent(eventName)
        }
    }
}