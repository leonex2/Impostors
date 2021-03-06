package com.example.o_bako.DatabaseSQLite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(var id : Int = 0,
            var Nama : String = "",
            var Deskripsi : String = "",
            var Qty : String = "",
            var Harga_Barang : String = "") : Parcelable