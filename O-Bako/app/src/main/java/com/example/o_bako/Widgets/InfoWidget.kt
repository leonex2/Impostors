package com.example.o_bako.Widgets

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.o_bako.Others.MySharedPreferenceHelper
import com.example.o_bako.R
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class InfoWidget : AppWidgetProvider() {
    private var myWidgetPreference : WidgetIDsHelper ?= null
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them

        if(myWidgetPreference == null){
            myWidgetPreference = WidgetIDsHelper(context)
        }
        var ids = myWidgetPreference?.getIds()
        myWidgetPreference?.getIds()?.clear()
        for (appWidgetId in appWidgetIds) {
            ids?.add(appWidgetId.toString())
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        if(ids!=null){
            myWidgetPreference?.setIds(ids)
        }
    }
    override fun onEnabled(context: Context) {
        var alarmIntent = Intent(context, InfoWidget::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context, 101,
                    it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE,1)

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, cal.timeInMillis,60000L,alarmIntent)
    }

    override fun onDisabled(context: Context) {
        var alarmIntent = Intent(context, InfoWidget::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context, 111,
                    it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(alarmIntent)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(ACTION_AUTO_UPDATE == intent?.action){
            if(myWidgetPreference == null)
                myWidgetPreference = WidgetIDsHelper(context!!)
            for(ids in myWidgetPreference?.getIds()!!){
                updateAppWidget(context!!, AppWidgetManager.getInstance(context),ids.toInt())
            }
        }
        super.onReceive(context, intent)
    }
    companion object{
        var msgList = MessageList()
        private var filenames = "MySharedFiles"
        val ACTION_AUTO_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"
        internal fun updateAppWidget(
                context: Context,
                appWidgetManager: AppWidgetManager,
                appWidgetId: Int) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.info_widget)
            var myPesan = msgList.getMessage()
            var myPref = MySharedPreferenceHelper(context,filenames)
            var namaUser = myPref?.nama
            var description = "Hello ${namaUser}. ${myPesan}"
            var myOtherInfo = "Sorry we got no new Promos."
            if (namaUser == ""){
                views.setTextViewText(R.id.appInfo, "Hello, ${myPesan}")
            }
            else{
                views.setTextViewText(R.id.appInfo, description)
            }
            views.setTextViewText(R.id.anotherInfo,myOtherInfo)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

