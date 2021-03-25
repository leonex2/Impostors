package com.example.o_bako.services

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.JobIntentService
import com.example.o_bako.DELAY_DONE
import com.example.o_bako.DELAY_TIME
import com.example.o_bako.PROG_DELAY
import com.example.o_bako.JOB_ID

class LoadingService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        var progressBar = 0
        do{
            Thread.sleep(10)
            progressBar+=1
            var intentFileDownload = Intent(PROG_DELAY)
            intentFileDownload.putExtra(DELAY_TIME,progressBar)
            intentFileDownload.putExtra(DELAY_DONE,false)
            if(progressBar>=100){
                intentFileDownload.putExtra(DELAY_DONE,true)
            }
            sendBroadcast(intentFileDownload)
        }while(progressBar < 100)
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"To MainHome", Toast.LENGTH_SHORT).show()
    }
    companion object{
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context,LoadingService::class.java, JOB_ID, intent)
        }
    }
}