package com.example.o_bako.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.o_bako.R


class MyAlarm : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        val notifi_id = 191
        val channel_id = "Alarm_Manager"
        val name = "Reminder"
        val importance = NotificationManager.IMPORTANCE_HIGH

        var notifi_manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifi_channel = NotificationChannel(channel_id, name, importance)

        notifi_channel.setShowBadge(true)
        notifi_channel.enableLights(true)
        notifi_channel.enableVibration(true)

        var notifi_builder = NotificationCompat.Builder(context, channel_id)
                .setContentTitle("Quick Notification")
                .setContentText("Testing")
                .setSmallIcon(R.drawable.icon_blue)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.icon_blue))
                .setShowWhen(true)
//        for (s in notifi_manager.notificationChannels) {
//            notifi_manager.deleteNotificationChannel(s.id)
//        }
        notifi_manager.createNotificationChannel(notifi_channel)
        notifi_manager.notify(notifi_id, notifi_builder.build())
    }
}