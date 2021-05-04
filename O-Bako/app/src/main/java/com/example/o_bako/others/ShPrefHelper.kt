package com.example.o_bako.others

import android.content.Context
import android.content.SharedPreferences

class ShPrefHelper(context : Context, filename : String) {
    val TEMP_USERNAME = "TEMP_USERNAME"
    val TEMP_PASSWORD = "TEMP_PASSWORD"
    val TEMP_EMAIL = "TEMP_EMAIL"

    private val myPreference : SharedPreferences
    init{
        myPreference = context.getSharedPreferences(filename,Context.MODE_PRIVATE)
    }
    var username : String?
        get() = myPreference.getString(TEMP_USERNAME,"")
        set(value){
            myPreference.edit().putString(TEMP_USERNAME,value).apply()
        }
    var password : String?
        get() = myPreference.getString(TEMP_PASSWORD,"")
        set(value){
            myPreference.edit().putString(TEMP_PASSWORD,value).apply()
        }
    var email : String?
        get() = myPreference.getString(TEMP_EMAIL,"")
        set(value){
            myPreference.edit().putString(TEMP_EMAIL,value).apply()
        }
    fun clearValues(){
        myPreference.edit().clear().apply()
    }
}