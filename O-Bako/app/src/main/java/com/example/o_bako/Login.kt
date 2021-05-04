package com.example.o_bako

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.SoundPool
import android.os.BatteryManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

private var login_soundpool : SoundPool? = null
private var login_soundID = 0
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            if(input_login.text.toString() != "" && input_password.text.toString() != ""){
                if(login_soundID!=0) {
                    login_soundpool?.play(login_soundID, .99f, .99f, 1, 0, .99f)
                }
                val intentToMainHome = Intent(this,MainActivity::class.java)
                var my_Login = input_login.text.toString()
                intentToMainHome.putExtra(EXTRA_USERNAME,my_Login)
                input_login.setText("")
                input_password.setText("")
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                startActivity(intentToMainHome)
            }
            else{
                Toast.makeText(this, "Harap isi Username dan Password!", Toast.LENGTH_SHORT).show()
            }
        }
        btn_signup.setOnClickListener{
            val intentSignup = Intent(this,SignUp::class.java)
            startActivityForResult(intentSignup, EXTRA_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EXTRA_REQUEST_CODE){
            if(resultCode == EXTRA_RESULT_CODE){
                input_login.setText (data?.getStringExtra(EXTRA_USERNAME) ?: "")
                input_password.setText(data?.getStringExtra(EXTRA_PASSWORD) ?: "")
            }
            else if (resultCode == EXTRA_CANCEL_CODE){
                input_login.setHint("Username")
                input_password.setHint("Password")
            }
        }
    }

    override fun onStart(){
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            createNewSoundPool()
        else
            createOldSoundPool()

        login_soundpool?.setOnLoadCompleteListener{soundPool, id, status ->
            if(status != 0)
                Toast.makeText(this,"Gagal Load",Toast.LENGTH_SHORT)
                    .show()
            else
                Toast.makeText(this,"Load Sukses",Toast.LENGTH_SHORT)
                    .show()
        }
        login_soundID = login_soundpool?.load(this, R.raw.mixkit_retro_game,1) ?: 0

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool() {
        login_soundpool = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }
    @Suppress("DEPRECATION")
    private fun createOldSoundPool() {
        login_soundpool = SoundPool(15, AudioManager.STREAM_MUSIC,0)
    }

    override fun onStop() {
        super.onStop()
        login_soundpool?.release()
        login_soundpool = null

    }
}