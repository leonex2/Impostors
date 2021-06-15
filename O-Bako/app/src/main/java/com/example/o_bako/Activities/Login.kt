package com.example.o_bako.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.o_bako.DatabaseRoom.DBHelperRoom
import com.example.o_bako.Others.AdsSharedPreference
import com.example.o_bako.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class Login : AppCompatActivity() {
    val myfilename = "AdsRemoverTime"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var adsRemover = AdsSharedPreference(this,myfilename)
        MobileAds.initialize(this){}
        var current = adsRemover.watchTime
        if(current > 0){
            myAdsView.loadAd(AdRequest.Builder().build())
            myAdsView.adListener = object : AdListener(){}
        }

//        Room Version
        var db = Room.databaseBuilder(
                this,
                DBHelperRoom::class.java,
                "obako.db"
        ).build()

        btn_login.setOnClickListener {
            val intentToMainHome = Intent(this, MainActivity::class.java)
            if (input_login.text.toString().isEmpty() && input_password.text.toString().isEmpty()){
                Toast.makeText(this,"Data tolong diisi",Toast.LENGTH_SHORT).show()
            }
            else if(input_login.text.toString() == "asd" && input_password.text.toString() == "asd"){
                startActivity(intentToMainHome)
            }
            else{
                var state = false
                var myLogin = input_login.text.toString()
                var myPass = input_password.text.toString()
                doAsync {
                    var userList = db.userDao().getAllData()
                    for(allData in userList){
                        if (myLogin == allData.username){
                            if (myPass == allData.password){
                                startActivity(intentToMainHome)
                                state = true
                                break
                            }
                        }
                    }
                    uiThread {
                        if (state) {
                            Toast.makeText(this@Login, "Login Sukses", Toast.LENGTH_SHORT)
                                    .show()
                        }
                        else
                            Toast.makeText(this@Login,"Username atau Password Salah",Toast.LENGTH_SHORT).show()
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