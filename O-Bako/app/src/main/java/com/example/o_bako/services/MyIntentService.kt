package com.example.o_bako.services

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.app.JobIntentService
import com.example.o_bako.EXTRA_WAKTU

private const val JOB_ID = 12345

class MyIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        if(intent!=null){
            var duration = intent.getLongExtra(EXTRA_WAKTU,0L)
            try {
                for(i in 0..2) {
                    Thread.sleep(duration)
                }
            }
            catch (e:Exception){}
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        myToast("Akun Berhasil dibuat !")
    }
    val mHandler: Handler = Handler(Looper.getMainLooper())

    fun myToast(text : String=""){
        mHandler.post(Runnable {
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
        })
    }
    companion object{
        fun enqueueWork(context:Context, intent: Intent){
            enqueueWork(context,MyIntentService::class.java,JOB_ID,intent)
        }
    }
}