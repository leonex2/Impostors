package com.example.o_bako

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.o_bako.services.MyIntentService
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_register.setOnClickListener {
            Handler().postDelayed({
                var myService = Intent(this, MyIntentService::class.java)
                var user = reg_username.text.toString()
                var intent = Intent()
                if (user.isEmpty())
                    setResult(EXTRA_CANCEL_CODE, intent)
                else {
                    intent.putExtra(EXTRA_USERNAME, user)
                    setResult(EXTRA_RESULT_CODE, intent)

                    myService.putExtra(EXTRA_WAKTU,300L)
                    MyIntentService.enqueueWork(this,myService)
                }
                finish()
            },1000)
        }
    }
}