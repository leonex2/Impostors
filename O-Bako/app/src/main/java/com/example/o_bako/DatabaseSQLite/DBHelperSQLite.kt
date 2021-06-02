package com.example.o_bako.DatabaseSQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperSQLite(context : Context) :SQLiteOpenHelper(
        context, DATABASE_NAME,null, DB_VERSION) {
    companion object {
        private val DB_VERSION = 1
        private val DATABASE_NAME = "o-bako.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query = ("CREATE TABLE ${barangDB.tableBarang.TABLE_BARANG} " +
                "(${barangDB.tableBarang.COLUMN_NAMA} TEXT," +
                "${barangDB.tableBarang.COLUMN_DESKRIPSI} TEXT," +
                "${barangDB.tableBarang.COLUMN_QTY} TEXT," +
                "${barangDB.tableBarang.COLUMN_HARGA} TEXT)")
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(
                "DROP TABLE IF EXISTS " +
                "${barangDB.tableBarang.TABLE_BARANG}"
        )
        onCreate(db)
    }
    fun addData( data : Data): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(barangDB.tableBarang.COLUMN_NAMA, data.Nama)
            put(barangDB.tableBarang.COLUMN_DESKRIPSI, data.Deskripsi)
            put(barangDB.tableBarang.COLUMN_QTY,data.Qty)
            put(barangDB.tableBarang.COLUMN_HARGA, data.Harga_Barang)
        }
        val success = db.insert(
                barangDB.tableBarang.TABLE_BARANG,
                null, contentValues
        )
        db.close()
        return success
    }

    fun getBarang(): MutableList<Data> {
        val dataList : MutableList<Data> = mutableListOf()
        val query = "SELECT * " +
                "FROM ${barangDB.tableBarang.TABLE_BARANG}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLException) {
//            db.execSQL(query)
            return ArrayList()
        }

        var namaBarang = ""
        var deskripsiBarang = ""
        var hargaBarang = ""
        var qtyBarang = ""

        if(cursor.moveToFirst()){
            do{
                namaBarang = cursor.getString(
                        cursor.getColumnIndex(barangDB.tableBarang.COLUMN_NAMA)
                )
                deskripsiBarang = cursor.getString(
                        cursor.getColumnIndex(barangDB.tableBarang.COLUMN_DESKRIPSI)
                )
                hargaBarang = cursor.getString(
                        cursor.getColumnIndex(barangDB.tableBarang.COLUMN_HARGA)
                )
                qtyBarang = cursor.getString(
                        cursor.getColumnIndex(barangDB.tableBarang.COLUMN_QTY)
                )
                dataList.add(Data(namaBarang,deskripsiBarang,qtyBarang,hargaBarang))
            }while(cursor.moveToNext())
        }
        return dataList
    }

    fun deleteData(){
        var db = this.writableDatabase
        db.delete(barangDB.tableBarang.TABLE_BARANG,null,null)
    }

    fun beginDataTransaction(){
        this.writableDatabase.beginTransaction()
    }

    fun successDataTransaction(){
        this.writableDatabase.setTransactionSuccessful()
    }
    fun endDataTransaction(){
        this.writableDatabase.endTransaction()
    }
    fun addDataTranscation(data : Data){
        var query = "INSERT INTO ${barangDB.tableBarang.TABLE_BARANG} " +
                "(${barangDB.tableBarang.COLUMN_NAMA}" +
                "${barangDB.tableBarang.COLUMN_DESKRIPSI}" +
                "${barangDB.tableBarang.COLUMN_QTY}" +
                "${barangDB.tableBarang.COLUMN_HARGA}) VALUES (?,?,?,?)"
        val statement = this.writableDatabase.compileStatement(query)
        statement.bindString(1,data.Nama)
        statement.bindString(2,data.Deskripsi)
        statement.bindString(3,data.Qty)
        statement.bindString(4,data.Harga_Barang)
        statement.execute()
        statement.clearBindings()
    }
}