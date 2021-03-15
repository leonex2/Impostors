package com.example.o_bako

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btn_register.setOnClickListener {
//            Membuat var user yang berisi text dari id reg_username
            var user = reg_username.text.toString()
//            kita perlu membentuk sebuah object intent agar data bisa dipassing ke MainActivity
            var intent = Intent()
//            melakukan pengecekan apakah var user kosong, maka kita mengembalikan result EXTRA_CANCEL_CODE
            if (user.isEmpty())
                setResult(EXTRA_CANCEL_CODE, intent)
//            jika tidak, maka akan dikembalikan value yang akan disimpan kedalam sebuah key EXTRA_USERNAME
//            agar bisa digunakan pada MainActivity.kt
            else {
                intent.putExtra(EXTRA_USERNAME, user)
                setResult(EXTRA_RESULT_CODE, intent)
            }
            finish()
        }
    }
}