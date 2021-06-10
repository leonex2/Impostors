package com.example.o_bako.Widgets

import android.content.Context

val PREF_NAME = "MyPref"
val KEYS_WIDGET_IDS = "widgetIds"
class WidgetIDsHelper(context: Context) {
    val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun setIds(ids : MutableSet<String>){
        val editor = pref.edit()
        editor.putStringSet(KEYS_WIDGET_IDS,ids)
        editor.apply()
    }
    fun getIds() = pref.getStringSet(KEYS_WIDGET_IDS,hashSetOf())
}