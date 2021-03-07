package com.example.o_bako

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var user = intent?.getStringExtra(EXTRA_USERNAME)?:""
        var psword = intent?.getStringExtra(EXTRA_PASSWORD)?:""
        btn_register.setOnClickListener {
            var user  = reg_username.text.toString()
            var intent = Intent()
            if(user.isEmpty())
                setResult(EXTRA_CANCEL_CODE,intent)
            else {
                intent.putExtra(EXTRA_USERNAME,user)
                setResult(EXTRA_RESULT_CODE, intent)
            }
            finish()
        }
    }
}