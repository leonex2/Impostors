package com.example.o_bako

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.o_bako.fragments.MainHome
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_signup.setOnClickListener{
            val intentSignup = Intent(this,SignUp::class.java)
            startActivityForResult(intentSignup, EXTRA_REQUEST_CODE)
        }
    }
//    Fungsi onClick pada Button Login di XML
//    Berfungsi sebagai Intent dari Login ke MainActivity
//    kemudian akan menyimpan data myData pada sebuah KeyExtra
    fun moveFragment(view : View){
        val intentToMain = Intent(this,MainActivity::class.java)
        var myData = input_login.text.toString()
        intentToMain.putExtra(EXTRA_USERNAME,myData)
        startActivity(intentToMain)
    }

//      Digunakan untuk menangkap hasil(result) dari SignUp melalui pengecekan requestCode, resultCode
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EXTRA_REQUEST_CODE){
            if(resultCode == EXTRA_RESULT_CODE){
                //Men-set Text input_login berdasarkan value yang tersimpan pada Key EXTRA_USERNAME pada SignUp.kt
                input_login.setText (data?.getStringExtra(EXTRA_USERNAME) ?: "")
            }
            else if (resultCode == EXTRA_CANCEL_CODE){
                input_login.setText("Kosong aja")
            }
        }
    }
}