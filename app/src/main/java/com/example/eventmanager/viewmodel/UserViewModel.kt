package com.example.eventmanager.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.eventmanager.database.Event
import com.example.eventmanager.database.EventImage
import com.example.eventmanager.database.User
import com.example.eventmanager.database.UserDB

import kotlinx.coroutines.launch

/**
 * Provides data to the UI
 * Data base can be accessed via view model only
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDB = UserDB.get(application)
    private val _image: MutableLiveData<Bitmap> = MutableLiveData()
    val image: LiveData<Bitmap> = _image

    private val _imageFromDb: MutableLiveData<Bitmap> = MutableLiveData()
    val imageFromDb: LiveData<Bitmap> = _imageFromDb
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

    fun deleteUser(userName: String) {
        viewModelScope.launch {
            userDB.userDao().deleteUser(userName)
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
     * @param eventName
     * Updates event in the database
     */
    fun deleteEvent(eventName: String) {
        viewModelScope.launch {
            userDB.eventDao().deleteEvent(eventName)
        }
    }

    // Converting URL image to bitmap
    suspend fun getBitmap(context: Context, imageUri: String) {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUri)
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        _image.value = (result as BitmapDrawable).bitmap
    }

    fun addImage(eventName: String) {

        viewModelScope.launch {
            if(image.value != null){
                userDB.ImageDao().addImage(EventImage(0,eventName, image.value!!))
            }
/*            .value?.let { EventImage(0, eventName, it) }
                ?.let { userDB.ImageDao().addImage(it)}*/
        }
    }

    fun getAllImage(): LiveData<List<EventImage>> =
        userDB.ImageDao().getImageByEventName()
}