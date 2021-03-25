package com.example.o_bako.others

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(Settings.System.getInt(context.contentResolver,
                        Settings.Global.AIRPLANE_MODE_ON,0)==0){
            Toast.makeText(context,"Flight Mode Mati", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"Flight Mode Hidup", Toast.LENGTH_LONG).show()
        }
    }
}