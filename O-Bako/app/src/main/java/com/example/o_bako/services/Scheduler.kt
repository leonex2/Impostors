package com.example.o_bako.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.o_bako.EXTRA_IDR
import com.example.o_bako.MainActivity
import com.example.o_bako.R
import com.example.o_bako.others.Konversi
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class Scheduler : JobService() {

    val key = "abaf500f7e308730a18d"
    val kode = "USD_IDR"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartJob(params: JobParameters?): Boolean {
        getNotified()
        getKonversi(params)
        Log.w("TAG", "Mulai")
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("TAG", "Selesai")
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNotified() {
        val notifi_id = 193
        val channel_id = "Promo Reminder"
        val name = "Promo"
        val importance = NotificationManager.IMPORTANCE_HIGH

        var myNotifyManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var myNotifyChannel = NotificationChannel(channel_id, name, importance)

        myNotifyChannel.setShowBadge(true)
        myNotifyChannel.enableLights(true)
        myNotifyChannel.enableVibration(true)

        var myNotifyBuild = NotificationCompat.Builder(this, channel_id)
            .setContentTitle("Oho, Check it out")
            .setContentText("Check Our Shop Now ! We got some HOT DEALS")
            .setSmallIcon(R.drawable.icon_blue)
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon_blue))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setShowWhen(true)

//        for (s in myNotifyManager.notificationChannels) {
//            myNotifyManager.deleteNotificationChannel(s.id)
//        }
        myNotifyManager.createNotificationChannel(myNotifyChannel)
        myNotifyManager.notify(notifi_id, myNotifyBuild.build())
    }

    private fun getKonversi(p0: JobParameters?) {
        var client = AsyncHttpClient()
        var url = "https://free.currconv.com/api/v7/convert?q=$kode&compact=ultra&apiKey=$key"
        val charSet = Charsets.UTF_8
        var handler = object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                var result = responseBody?.toString(charSet) ?: "Kosong"
                val obj = JSONObject(result)
                val hasil = obj.getString("USD_IDR")
                Log.w("Hasil",hasil)
                jobFinished(p0, false)
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                jobFinished(p0, true)
            }
        }
        client.get(url, handler)
    }
}