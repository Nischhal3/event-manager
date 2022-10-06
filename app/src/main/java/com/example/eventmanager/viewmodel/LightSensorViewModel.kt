package com.example.eventmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LightSensorViewModel : ViewModel() {
    private val _light: MutableLiveData<String> = MutableLiveData()
    val light: LiveData<String> = _light

    fun updateValue(temperature: String) {
        _light.value = temperature
    }
}
