package com.example.o_bako.Others

import android.content.Context
import android.content.SharedPreferences
import com.example.o_bako.TEMP_HOMEADDRESS
import com.example.o_bako.TEMP_NAMA
import com.example.o_bako.TEMP_PHONE

class MySharedPreferenceHelper(context : Context, filename : String) {
    val TEMP_USERNAME = "TEMP_USERNAME"
    val TEMP_PASSWORD = "TEMP_PASSWORD"
    val TEMP_EMAIL = "TEMP_EMAIL"

    private val myPreference : SharedPreferences
    init{
        myPreference = context.getSharedPreferences(filename,Context.MODE_PRIVATE)
    }
    var nama : String?
        get() = myPreference.getString(TEMP_NAMA,"")
        set(value){
            myPreference.edit().putString(TEMP_NAMA,value).apply()
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
    var home_address : String?
        get() = myPreference.getString(TEMP_HOMEADDRESS,"")
        set(value){
            myPreference.edit().putString(TEMP_HOMEADDRESS,value).apply()
        }
    var email : String?
        get() = myPreference.getString(TEMP_EMAIL,"")
        set(value){
            myPreference.edit().putString(TEMP_EMAIL,value).apply()
        }
    var phone_number : String?
        get() = myPreference.getString(TEMP_PHONE,"")
        set(value){
            myPreference.edit().putString(TEMP_PHONE,value).apply()
        }
    fun clearValues(){
        myPreference.edit().clear().apply()
    }
}