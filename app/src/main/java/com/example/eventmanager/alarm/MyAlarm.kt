package com.example.eventmanager.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.SystemClock.sleep
import android.provider.Settings
import android.util.Log

/**
 * Alarm notification receiver
 * Upon receiving alarm info plays Alarm ringtone for 10 secs
 */
class MyAlarm : BroadcastReceiver() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)
            mediaPlayer.start()
            mediaPlayer.isLooping
            sleep(10000)
            mediaPlayer.stop()

        } catch (e: Exception) {
            Log.d("Error", e.printStackTrace().toString())
        }
    }
}