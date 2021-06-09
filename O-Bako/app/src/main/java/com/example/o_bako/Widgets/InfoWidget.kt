package com.example.o_bako.Widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.o_bako.Others.MySharedPreferenceHelper
import com.example.o_bako.R

/**
 * Implementation of App Widget functionality.
 */
class InfoWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    companion object{
        private var filenames = "MySharedFiles"
        internal fun updateAppWidget(
                context: Context,
                appWidgetManager: AppWidgetManager,
                appWidgetId: Int) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.info_widget)
            var myPref = MySharedPreferenceHelper(context,filenames)
            var namaUser = myPref?.nama
            var description = "Hello ${namaUser}. Have a Good Day!"
            var myOtherInfo = "Sorry we got no new Promos."
            if (namaUser == ""){
                views.setTextViewText(R.id.appInfo, "Hello, Great Person")
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

