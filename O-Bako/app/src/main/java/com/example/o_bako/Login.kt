package com.example.o_bako

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.o_bako.services.LoadingService
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent.ACTION_POWER_CONNECTED as IntentACTION_POWER_CONNECTED


class Login : AppCompatActivity() {
    private val TAG = Login::class.simpleName
    private var tvBatteryLevel: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvBatteryLevel = findViewById(R.id.battery_lvl)
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver, intentFilter)
        btn_login.setOnClickListener {
            if(input_login.text.toString() != "" && input_password.text.toString() != ""){
                val intentToMainHome = Intent(this,MainActivity::class.java)
                var my_Login = input_login.text.toString()
                intentToMainHome.putExtra(EXTRA_USERNAME,my_Login)
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

    private val batteryBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?){
            if(intent?.action == "android.intent.action.BATTERY_CHANGED") {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                Log.d(TAG, "onReceive: battery level $level")
                tvBatteryLevel?.post {
                    tvBatteryLevel?.text = level.toString().plus(" ").plus("%")
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryBroadcastReceiver)
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
}