package com.example.o_bako.Provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.o_bako.DatabaseSQLite.DBHelperSQLite
import com.example.o_bako.DatabaseSQLite.Data
import com.example.o_bako.DatabaseSQLite.barangDB

class MyContentProvider : ContentProvider() {
    private var mySQLiteHelper : DBHelperSQLite ?= null
    override fun onCreate(): Boolean {
        mySQLiteHelper = DBHelperSQLite(context!!)
        return true
    }
    override fun query(uri: Uri,
                       projection: Array<out String>?,
                       selection: String?,
                       selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor? {
        var queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = barangDB.tableBarang.TABLE_BARANG
        var cursor : Cursor = queryBuilder.query(mySQLiteHelper?.readableDatabase,
                arrayOf(barangDB.tableBarang.COLUMN_NAMA),selection,selectionArgs,null,null,sortOrder)
        cursor.setNotificationUri(context?.contentResolver,uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
//        var queryBuilder = SQLiteQueryBuilder()
//        queryBuilder.insert()
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
//        var queryBuilder = SQLiteQueryBuilder()
//        queryBuilder.delete()
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
//        var queryBuilder = SQLiteQueryBuilder()
//        queryBuilder.update()
    }

    companion object{
        val AUTHORITY = "com.example.o_bako.Provider.provider.MyContentProvider"
        val BARANG_TABLE = barangDB.tableBarang.TABLE_BARANG
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BARANG_TABLE")
    }
}