package com.example.o_bako

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

//Berfungsi sebagai Key EXTRA untuk pengerjaan onSavedInstanceState
private const val EXTRA_ONSAVED = "EXTRA_ONSAVED"

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var user = intent?.getStringExtra(EXTRA_USERNAME)?:""
        var psword = intent?.getStringExtra(EXTRA_PASSWORD)?:""
        btn_register.setOnClickListener {
            infotxt.text = reg_username.text
            reg_username.setText("")
//            var user  = reg_username.text.toString()
//            var intent = Intent()
//            if(user.isEmpty())
//                setResult(EXTRA_CANCEL_CODE,intent)
//            else {
//                intent.putExtra(EXTRA_USERNAME,user)
//                setResult(EXTRA_RESULT_CODE, intent)
//            }
//            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        Menyimpan Text dari infotxt kedalam EXTRA_ONSAVED (Key) agar tidak text tidak hilang ketika-
//        terjadi rotasi ataupun perubahan lainnya
//        apabila yang kita simpan adalah berupa text maka jangan lupa memasukan .text didalamnya karena-
//        apabila kita lupa maka text yang ditampilkan akan berupa pesan error seperti dibawah
//        androidx.appcompact.widget.appcompacttextview{595b8ebv.ed...... app:id/id
        outState.putString(EXTRA_ONSAVED,infotxt.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //untuk mengembalikan data yang telah tersimpan didalam EXTRA_ONSAVED (Key)
        infotxt.text = savedInstanceState?.getString(EXTRA_ONSAVED) ?: "Null"
    }
}