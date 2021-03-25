package com.example.o_bako

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.o_bako.services.LoadingService
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private val progressRec = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var selesai = intent?.getBooleanExtra(DELAY_DONE, true)
            var persen = intent?.getIntExtra(DELAY_TIME, 0)
            myProgress.progress = persen ?: 0
        }
    }
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
        Handler().postDelayed({
            val intentToMain = Intent(this,MainActivity::class.java)
            var myData = input_login.text.toString()
            intentToMain.putExtra(EXTRA_USERNAME,myData)
            startActivity(intentToMain)
        },4500)

        var myService = Intent(this, LoadingService::class.java)
        LoadingService.enqueueWork(this,myService)
        var filterProg = IntentFilter(PROG_DELAY)
        registerReceiver(progressRec,filterProg)
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(progressRec)
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