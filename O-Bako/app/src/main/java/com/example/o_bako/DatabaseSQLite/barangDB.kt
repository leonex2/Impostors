package com.example.o_bako.DatabaseSQLite

import android.provider.BaseColumns

object barangDB {
    class tableBarang: BaseColumns {
        companion object {
            val TABLE_BARANG = "Barang"
<<<<<<< HEAD
            val COLUMN_ID = "ID"
            val COLUMN_NAMA = "Nama"
            val COLUMN_DESKRIPSI = "Deskripsi"
            val COLUMN_QTY = "1"
            val COLUMN_HARGA = "Harga"
=======
            val COLUMN_NAMA: String = "Nama"
            val COLUMN_DESKRIPSI: String = "Deskripsi"
            val COLUMN_QTY : String = "qwq"
            val COLUMN_HARGA : String = "Harga"
>>>>>>> 488eee0467fce7c4ff76331531b2b08483b9bbdb
        }
    }
}