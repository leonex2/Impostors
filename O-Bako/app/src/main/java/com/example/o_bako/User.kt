package com.example.o_bako

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var Username : String = "Null",
                var Password : String = "Null" ,
                var Email : String = "Null") : Parcelable {
}