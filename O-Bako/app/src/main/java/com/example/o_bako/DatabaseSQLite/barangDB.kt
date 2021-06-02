package com.example.o_bako.DatabaseSQLite

import android.provider.BaseColumns

object barangDB {
    class tableBarang: BaseColumns {
        companion object {
            val TABLE_BARANG = "Barang"
            val COLUMN_NAMA: String = "Nama"
            val COLUMN_DESKRIPSI: String = "Deskripsi"
            val COLUMN_QTY : String = "0"
            val COLUMN_HARGA : String = "Harga"
        }
    }
}