package com.example.o_bako

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User : Parcelable {
    var id: Int = 0
    var username : String = ""
    var password : String = ""
    var nama_user : String = ""
    var nomor_hp : String = ""
    var alamat : String = ""
}
