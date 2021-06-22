package com.example.o_bako.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.o_bako.EXTRA_PASSWORD
import com.example.o_bako.EXTRA_USERNAME
import com.example.o_bako.Firebase.FirebaseController
import com.example.o_bako.Firebase.Users
import com.example.o_bako.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    lateinit var myController : FirebaseController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        myController = FirebaseController(this)
        btn_register.setOnClickListener {
            val intentToLogin = Intent(this, Login::class.java)
            var user_name = reg_name.text.toString()
            var username = reg_username.text.toString()
            var user_pw = reg_passwd.text.toString()
            var user_homeaddress = reg_alamat.text.toString()
            var user_email = reg_email.text.toString()
            var user_phone = reg_phonenumber.text.toString()
            if(user_name.isEmpty() || username.isEmpty() || user_pw.isEmpty() || user_homeaddress.isEmpty() || user_homeaddress.isEmpty() || user_email.isEmpty() || user_phone.isEmpty()){
                Toast.makeText(this,"Fields are Empty!", Toast.LENGTH_SHORT).show()
            }
            else{
                myController.saveData(Users(
                    user_name, username, user_pw, user_email, user_homeaddress, user_phone)
                )
                intentToLogin.putExtra(EXTRA_USERNAME, username)
                intentToLogin.putExtra(EXTRA_PASSWORD, user_pw)
                startActivity(intentToLogin)
            }
        }
    }
}