package com.example.app3

import android.content.Context
import com.example.app3.BarangDB.tableBarang.Companion.COLUMN_DESKRIPSI
import com.example.app3.BarangDB.tableBarang.Companion.COLUMN_HARGA
import com.example.app3.BarangDB.tableBarang.Companion.COLUMN_ID
import com.example.app3.BarangDB.tableBarang.Companion.COLUMN_NAMA
import com.example.app3.BarangDB.tableBarang.Companion.COLUMN_QTY

class Transaction(context: Context){
    private var myContentResolver = context.contentResolver

    fun viewData() : List<String>{
        var myBarang = ArrayList<String>()
        var myProjection = arrayOf(COLUMN_ID, COLUMN_NAMA, COLUMN_DESKRIPSI, COLUMN_QTY,
            COLUMN_HARGA)
        var cursor = myContentResolver.query(
            myContentProviderURI.CONTENT_URI,myProjection,null,null,null
        )

        if(cursor!=null){
            var namaBarang : String = ""
            if(cursor.moveToNext()){
                do{
                    namaBarang = cursor.getString(
                        cursor.getColumnIndex(BarangDB.tableBarang.COLUMN_NAMA)
                    )
                    myBarang.add(namaBarang)
                }while(cursor.moveToNext())
            }
        }
        return myBarang
    }
}