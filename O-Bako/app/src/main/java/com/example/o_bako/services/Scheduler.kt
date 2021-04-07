package com.example.o_bako.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.o_bako.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class Scheduler : JobService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartJob(params: JobParameters?): Boolean {
        getNotified()
        Log.w("TAG","Mulai")
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("TAG","Selesai")
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNotified() {
        val notifi_id = 193
        val channel_id = "Promo Reminder"
        val name = "Promo"
        val importance = NotificationManager.IMPORTANCE_HIGH

        var myNotifyManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var myNotifyChannel = NotificationChannel(channel_id, name, importance)

        myNotifyChannel.setShowBadge(true)
        myNotifyChannel.enableLights(true)
        myNotifyChannel.enableVibration(true)

        var myNotifyBuild = NotificationCompat.Builder(this, channel_id)
                .setContentTitle("Oho, Check it out")
                .setContentText("Aye, We got a new Deal for you !")
                .setSmallIcon(R.drawable.icons8_notifications)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon_blue))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setShowWhen(true)

//        for (s in myNotifyManager.notificationChannels) {
//            myNotifyManager.deleteNotificationChannel(s.id)
//        }
        myNotifyManager.createNotificationChannel(myNotifyChannel)
        myNotifyManager.notify(notifi_id, myNotifyBuild.build())
    }

}