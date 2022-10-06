package com.example.eventmanager

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmanager.navigation.AuthNavigationScreens
import com.example.eventmanager.screens.loginScreen
import com.example.eventmanager.screens.Register
import com.example.eventmanager.ui.theme.EventManagerTheme
import com.example.eventmanager.viewmodel.AmbientTemperatureViewModel
import com.example.eventmanager.viewmodel.UserViewModel

class MainActivity : ComponentActivity(), SensorEventListener {
    companion object{
        private lateinit var userViewModel: UserViewModel
        private lateinit var userName: MutableState<String>

        private lateinit var sm: SensorManager
        private var slight: Sensor? = null
        private val mTemperatureViewModel = AmbientTemperatureViewModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel(application)

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
       slight = sm.getDefaultSensor(Sensor.TYPE_LIGHT)

        setContent {
            EventManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = AuthNavigationScreens.Login.route) {
                        composable(AuthNavigationScreens.Login.route) {
                            userName = loginScreen(navController, userViewModel)
                        }
                        composable(AuthNavigationScreens.Register.route) { Register(navController, userViewModel) }
                        composable(AuthNavigationScreens.Main.route) { MainFragment(navController, userName, userViewModel) }
                    }
                }
            }
        }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (event.sensor ==slight) {
            mTemperatureViewModel.updateValue(event.values[0].toString())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
        Log.d("Hello", "onAccuracyChange: ${sensor?.name}: $p1")
    }

    override fun onResume() {
        super.onResume()
       slight?.also {
            sm.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sm.unregisterListener(this)
    }
    private fun brightness(brightness: Float): String{
        return when(brightness.toInt()){
            0 -> "Pitch black"
            in 1..10 -> "Dark"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "Light"
            else -> "this is lit"
        }
    }
}