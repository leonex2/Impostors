package com.example.app3

import android.net.Uri
import android.provider.BaseColumns

object BarangDB {
    class tableBarang: BaseColumns {
        companion object {
            val TABLE_BARANG = "Barang"
            val COLUMN_ID = "ID"
            val COLUMN_NAMA: String = "Nama"
            val COLUMN_DESKRIPSI: String = "Deskripsi"
            val COLUMN_QTY : String = "Quantity"
            val COLUMN_HARGA : String = "Harga"
        }
    }
}

class myContentProviderURI{
    companion object{
        val AUTHORITY = "com.example.o_bako.Provider.provider.MyContentProvider"
        val BARANG_TABLE = BarangDB.tableBarang.TABLE_BARANG
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BARANG_TABLE")
    }
}