package com.example.o_bako

import android.content.Context

class PreLoaderShared (context : Context){
    private val keyPref = "FIRST_RUN"
    private var myShared = context.getSharedPreferences(
            "BarangSharePreference", Context.MODE_PRIVATE)
    var firstRun : Boolean
        get() = myShared.getBoolean(keyPref,true)
        set(value) {
            myShared.edit().putBoolean(keyPref, value).commit()
        }
}