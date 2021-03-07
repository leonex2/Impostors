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

            //Tanpa Parcelize
//            IntentHome.putExtra(EXTRA_USERDOANK,userku.Username)
//            IntentHome.putExtra(EXTRA_PASSWORD,userku.Password)
//            IntentHome.putExtra(EXTRA_EMAIL,userku.Email)
            //Parcelize
            var x = User("Impostors", "Testingajakok","akukeren@gmail.com")
            IntentHome.putExtra(EXTRA_USERDOANK,x)
            startActivity(IntentHome)
        }
        //Intent untuk berpindah ke Activity SignUp
        btn_signup.setOnClickListener {
            var IntentSignUp = Intent(this, SignUp::class.java)
            startActivityForResult(IntentSignUp, EXTRA_REQUEST_CODE)
        }
    }
}