package com.example.eventmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Provides light sensor data to the UI
 */
class LightSensorViewModel : ViewModel() {
    private val _light: MutableLiveData<String> = MutableLiveData()
    val light: LiveData<String> = _light

    // Storing light sensor data
    fun updateValue(data: String) {
        _light.value = data
    }
}
