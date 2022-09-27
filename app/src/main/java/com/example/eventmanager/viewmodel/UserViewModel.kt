package com.example.eventmanager.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eventmanager.database.Event
import com.example.eventmanager.database.User
import com.example.eventmanager.database.UserDB

import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDB = UserDB.get(application)

    // private val _username: MutableLiveData<String> = MutableLiveData()
    // val userName:  LiveData<String> = _username


    fun getAllUser(): LiveData<List<User>> =
        userDB.userDao().getAllUser()


    fun getUserByUserName(name: String): LiveData<User> =
        userDB.userDao().getUserByUserName(name)

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

    fun deleteUsers() {
        viewModelScope.launch {
            userDB.userDao().deleteAll()
        }
    }

    fun getAllEvent(): LiveData<List<Event>> =
        userDB.eventDao().getAllEvent()

    fun addEvent(event: Event) {
        viewModelScope.launch {
            userDB.eventDao().addEvent(event)
        }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch {
            userDB.eventDao().updateEvent(event)
        }
    }
}