package com.example.o_bako

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Intent Untuk Berpindah ke Activity HomePage
        btn_login.setOnClickListener {
            var IntentHome = Intent(this, HomePage::class.java)
            var user = input_login.text
            //EXTRA_USERNAME berperan sebagai Key *EXTRA_USERNAME BUKAN "EXTRA_USERNAME"
            //Jika Kita menggunakan "EXTRA_USERNAME" maka yang terpassing adalah "" -> Tidak ada
            //user berperan sebagai value
            IntentHome.putExtra(EXTRA_USERNAME, user.toString())

//            Tanpa Parcelize
//            var x = User("Parcelable Disini", "Testingajakok","akukeren@gmail.com")
//            IntentHome.putExtra(EXTRA_USERDOANK,x.Username)
//            IntentHome.putExtra(EXTRA_PASSWORD,x.Password)
//            IntentHome.putExtra(EXTRA_EMAIL,x.Email)
            //Parcelize
            var x = User("Parcelable Disini", "Testingajakok","akukeren@gmail.com")
            IntentHome.putExtra(EXTRA_USERDOANK,x)
            startActivity(IntentHome)
        }
        //Intent untuk berpindah ke Activity SignUp dan Menunggu hasil dari activity SignUp
        btn_signup.setOnClickListener {
            var IntentSignUp = Intent(this, SignUp::class.java)
            startActivityForResult(IntentSignUp, EXTRA_REQUEST_CODE)
        }
    }
//  Digunakan untuk menangkap hasil(result) dari SignUp melalui pengecekan requestCode, resultCode
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