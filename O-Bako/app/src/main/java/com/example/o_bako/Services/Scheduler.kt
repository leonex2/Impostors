package com.example.o_bako.Services

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
        getKonversi(params)
        Log.w("TAG", "Mulai")
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("TAG", "Selesai")
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNotified(get_curr : String) {
        val notifi_id = 193
        val channel_id = "Promo Reminder"
        val name = "Scheduler"
        val importance = NotificationManager.IMPORTANCE_HIGH

        var myNotifyManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var myNotifyChannel = NotificationChannel(channel_id, name, importance)

        myNotifyChannel.setShowBadge(true)
        myNotifyChannel.enableLights(true)
        myNotifyChannel.enableVibration(true)

        var myNotifyBuild = NotificationCompat.Builder(this, channel_id)
            .setContentTitle("Quick Notifications")
            .setContentText("Just for Info. Today's IDR to USD Rate : $get_curr")
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
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                var result = responseBody?.toString(charSet) ?: "Kosong"
                val obj = JSONObject(result)
                val get_curr = obj.getString("USD_IDR")
                Log.w("Hasil",get_curr)
                jobFinished(p0, true)
                getNotified(get_curr)

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