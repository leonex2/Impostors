package com.example.o_bako.others

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.o_bako.EXTRA_IDR
import com.example.o_bako.services.MyAlarm
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class Konversi : JobService() {

    val key = "abaf500f7e308730a18d"
    val kode = "USD_IDR"

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.w("Job","Mulai bang")
        getKonversi(p0)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.w("Job","Berhenti bang")
        return true
    }

    private fun getKonversi(p0: JobParameters?) {
        var client = AsyncHttpClient()
        var url = "https://free.currconv.com/api/v7/convert?q=$kode&compact=ultra&apiKey=$key"
        val charSet = Charsets.UTF_8
        var handler = object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                var result = responseBody?.toString(charSet) ?: "Kosong"
                Log.w("Hasil",result)
                jobFinished(p0,false)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                jobFinished(p0,true)
            }

        }
        client.get(url,handler)
    }
}