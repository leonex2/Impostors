package com.example.o_bako

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.o_bako.DatabaseRoom.DBHelperRoom
import com.example.o_bako.DatabaseRoom.UserRoom
import com.example.o_bako.others.ShPrefHelper
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.doAsync

@RequiresApi(Build.VERSION_CODES.KITKAT)
class SignUp : AppCompatActivity() {
    private val PrefFileName = "MySharedPreference"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        Room Version
        var db = Room.databaseBuilder(
            this,
            DBHelperRoom::class.java,
            "obako.db"
        ).build()

        btn_register.setOnClickListener {
            val intentToLogin = Intent(this,Login::class.java)
            var user_name = reg_name.text.toString()
            var username = reg_username.text.toString()
            var user_pw = reg_passwd.text.toString()
            var user_homeaddress = reg_alamat.text.toString()
            var user_email = reg_email.text.toString()
            var user_phone = reg_phonenumber.text.toString()
            var mySharedHelper = ShPrefHelper(this,PrefFileName)

//            Room Version
            var hasil = ""
            doAsync {
                db.userDao().insertUser(
                    UserRoom(id = 0,user_name,username,user_pw,
                        user_email,user_homeaddress,user_phone)
                )
                for(allData in db.userDao().getAllData()){
                    hasil += "${allData.nama_user} ${allData.username} ${allData.password}" +
                            "${allData.email} ${allData.alamat} ${allData.nomor_hp}"
                }
            }
            mySharedHelper.nama = user_name
            mySharedHelper.username = username
            mySharedHelper.password = user_pw
            mySharedHelper.home_address = user_homeaddress
            mySharedHelper.email = user_email
            mySharedHelper.phone_number = user_phone
            intentToLogin.putExtra(EXTRA_USERNAME, username)
            intentToLogin.putExtra(EXTRA_PASSWORD, user_pw)
            Toast.makeText(this,"Akun Berhasil dibuat!", Toast.LENGTH_SHORT).show()
            startActivity(intentToLogin)

        }
    }
//    External File Write&Read
//    private fun writeFileExternal() {
//        var myDir = File(getExternalFilesDir(""),"MyFolder")
//        if(!myDir.exists()){
//            myDir.mkdir()
//        }
//        File(myDir,"Sign Up.txt").apply {
//            writeText(reg_username.text.toString())
//        }
//    }
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun isExternalStorageReadable(): Boolean{
//        if(ContextCompat.checkSelfPermission( //Untuk mengecek Permission yang telah diberikan Aplikasi
//                        this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE //Permission yang diminta pada AndroidManifest
//                ) != PackageManager.PERMISSION_GRANTED){ //Apakah sudah Permission telah diberikan
//            requestPermissions( // Jika belum, maka akan diminta Permission untuk Read , Write pada External.
//                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    123)
//        }
//        //Mengecek State dari External Storage
//        var state = Environment.getExternalStorageState()
//        //Apakah sdcard sudah terpasang ? atau sdcard hanya bisa di read
//        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
//        {
//            return true
//        }
//        return false
//    }
//
//    override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<out String>,
//            grantResults: IntArray
//    ) { // Request Permission dalam Aplikasi yang akan mengembalikan sebuah Request Code
//        // Request code akan dikembalikan ke Fungsi isExternalStorageReadable() jika Access diberikan
//        when (requestCode) {
//            123 -> {
//                if ((grantResults.isNotEmpty() &&
//                                grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                ) {
//                    Toast.makeText(this, "Allowed !", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
}