package com.example.o_bako

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_profile.*

private const val EXTRA_ONSAVED = "EXTRA_ONSAVED"

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        var alamat = intent?.getStringExtra(EXTRA_ALAMAT)?:""

        button2.setOnClickListener {
            textView3.text = editTextTextPersonName.text
            editTextTextPersonName.setText("")
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        Menyimpan Text dari textView3 kedalam EXTRA_ONSAVED (Key) agar tidak text tidak hilang ketika-
//        terjadi rotasi ataupun perubahan lainnya
//        apabila yang kita simpan adalah berupa text maka jangan lupa memasukan .text didalamnya karena-
//        apabila kita lupa maka text yang ditampilkan akan berupa pesan error seperti dibawah
//        androidx.appcompact.widget.appcompacttextview{595b8ebv.ed...... app:id/id
        outState.putString(EXTRA_ONSAVED,textView3.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //untuk mengembalikan data yang telah tersimpan didalam EXTRA_ONSAVED (Key)
        textView3.text = savedInstanceState?.getString(EXTRA_ONSAVED) ?: "Null"
    }
}