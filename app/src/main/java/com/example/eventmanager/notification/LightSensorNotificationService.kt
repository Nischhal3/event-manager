package com.example.eventmanager.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.eventmanager.MainActivity
import com.example.eventmanager.R

class LightSensorNotificationService(
    private  val context: Context
) {
    private  val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(condition: String){
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_wb_sunny_24)
            .setContentTitle("Light sensor notification")
            .setContentText("It is $condition outside")
            .setContentIntent(activityPendingIntent)
            .setPriority(5)
            .setColor(5555)
            .build()

        notificationManager.notify(
            1, notification
        )
    }
    companion object{
        const val CHANNEL_ID = "light_channel"
    }
}