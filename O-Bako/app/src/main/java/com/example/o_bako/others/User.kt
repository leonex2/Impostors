package com.example.o_bako

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
//Untuk mengubah data class menjadi parcelable hanya perlu menambahkan @Parcelize sebelum data class
//kemudian menambahkan :Parcelable pada akhir dari data class
data class User(var Username : String = "Null",
                var Password : String = "Null" ,
                var Email : String = "Null") : Parcelable {
}