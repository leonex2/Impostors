package com.example.o_bako

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
        @PrimaryKey var id: Int = 0,
        @ColumnInfo(name = "COLUMN_NAME") var nama_user : String = "",
        @ColumnInfo(name = "COLUMN_USERNAME") var username : String = "",
        @ColumnInfo(name = "COLUMN_PASSWORD") var password : String = "",
        @ColumnInfo(name = "COLUMN_EMAIL") var email : String = "",
        @ColumnInfo(name = "COLUMN_ALAMAT") var alamat : String = "",
        @ColumnInfo(name = "COLUMN_NOHP") var nomor_hp : String = ""
)
