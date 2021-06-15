package com.example.o_bako.Others

import android.content.Context
import android.content.SharedPreferences

class AdsSharedPreference(context : Context, filename : String) {
    val TEMP_TIME = "TEMP_TIME"
    private val myPreference : SharedPreferences
    init{
        myPreference = context.getSharedPreferences(filename,Context.MODE_PRIVATE)
    }
    var watchTime : Int
        get() = myPreference.getInt(TEMP_TIME,3)
        set(value){
            myPreference.edit().putInt(TEMP_TIME,value).apply()
        }
}