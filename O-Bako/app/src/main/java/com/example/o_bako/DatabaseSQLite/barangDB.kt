package com.example.o_bako.DatabaseSQLite

import android.provider.BaseColumns

object barangDB {
    class tableBarang: BaseColumns {
        companion object {
            val TABLE_BARANG = "Barang"
            val COLUMN_ID = "ID"
            val COLUMN_NAMA = "Nama"
            val COLUMN_DESKRIPSI = "Deskripsi"
            val COLUMN_QTY = "1"
            val COLUMN_HARGA = "Harga"
        }
    }
}