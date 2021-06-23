package com.example.o_bako.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.o_bako.Firebase.FirebaseController
import com.example.o_bako.Others.AdsSharedPreference
import com.example.o_bako.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class Login : AppCompatActivity() {
    val myfilename = "AdsRemoverTime"
    lateinit var myController : FirebaseController
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        myController = FirebaseController(this)
        myController.readData()
        var adsRemover = AdsSharedPreference(this,myfilename)
        MobileAds.initialize(this){}
        var current = adsRemover.watchTime
        if(current > 0){
            myAdsView.loadAd(AdRequest.Builder().build())
            myAdsView.adListener = object : AdListener(){}
        }
        btn_login.setOnClickListener {
            val intentToMainHome = Intent(this, MainActivity::class.java)
            if (input_login.text.toString().isEmpty() && input_password.text.toString().isEmpty()){
                Toast.makeText(this,"Data tolong diisi",Toast.LENGTH_SHORT).show()
            }
            else{
                doAsync {
                    var myLogin = input_login.text.toString()
                    var myPassword = input_password.text.toString()
                    var state = myController.myAuth(myLogin,myPassword)
                    Log.w("Status", state.toString())
                    uiThread {
                        if(state){
                            startActivity(intentToMainHome)
                            Toast.makeText(this@Login,"Login Success !", Toast.LENGTH_SHORT).show()
                        }
                        else
                            Toast.makeText(this@Login,"You're not Registered !", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        btn_signup.setOnClickListener{
            val intentSignup = Intent(this, SignUp::class.java)
            startActivity(intentSignup)
        }
    }

    override fun onResume() {
        super.onResume()
        var adsRemover = AdsSharedPreference(this,myfilename)
        var current = adsRemover.watchTime
        if(current > 0){
            myAdsView.loadAd(AdRequest.Builder().build())
            myAdsView.adListener = object : AdListener(){}
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == EXTRA_REQUEST_CODE){
//            if(resultCode == EXTRA_RESULT_CODE){
//                input_login.setText (data?.getStringExtra(EXTRA_USERNAME) ?: "")
//                input_password.setText(data?.getStringExtra(EXTRA_PASSWORD) ?: "")
//            }
//            else if (resultCode == EXTRA_CANCEL_CODE){
//                input_login.setHint("Username")
//                input_password.setHint("Password")
//            }
//        }
//    }
}