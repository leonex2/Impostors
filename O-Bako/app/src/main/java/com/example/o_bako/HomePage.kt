package com.example.o_bako

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_main.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var user = intent.getStringExtra(EXTRA_USERNAME) ?: ""
        hellotxt.setText("Selamat Datang $user di O-Bako")

//        Parcelize
        var x = intent.getParcelableExtra<User>(EXTRA_USERDOANK)
        txt_parcelize.text = "${x?.Username} Selamat Datang !"
    }
}