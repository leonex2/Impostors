package com.example.o_bako

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.o_bako.fragments.InterfaceInput
import com.example.o_bako.fragments.Login
import com.example.o_bako.fragments.MainHome

class MainActivity : AppCompatActivity(), InterfaceInput {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Untuk Memulai Fragment Login ketika Aplikasi dijalankan
        val fragment_login = Login()
        supportFragmentManager
                .beginTransaction().replace(R.id.myContainer,fragment_login).commit()

    }

    override fun inputan(my_inputan: String) {
        val bundle = Bundle()
        bundle.putString("Pesan",my_inputan)

        val passing = this.supportFragmentManager.beginTransaction()

        val fragment_home = MainHome()
        fragment_home.arguments = bundle

        passing.replace(R.id.myContainer,fragment_home)
        passing.addToBackStack(null)
        passing.commit()
    }
}

////      Intent Untuk Berpindah ke Activity HomePage
//        btn_login.setOnClickListener {
//            var IntentHome = Intent(this, HomePage::class.java)
//            var user = input_login.text
//            //EXTRA_USERNAME berperan sebagai Key *EXTRA_USERNAME BUKAN "EXTRA_USERNAME"
//            //Jika Kita menggunakan "EXTRA_USERNAME" maka yang terpassing adalah "" -> Tidak ada
//            //user berperan sebagai value
//            IntentHome.putExtra(EXTRA_USERNAME, user.toString())
//
////            Tanpa Parcelize
////            var x = User("Parcelable Disini", "Testingajakok","akukeren@gmail.com")
////            IntentHome.putExtra(EXTRA_USERDOANK,x.Username)
////            IntentHome.putExtra(EXTRA_PASSWORD,x.Password)
////            IntentHome.putExtra(EXTRA_EMAIL,x.Email)
//            //Parcelize
//            var x = User("Parcelable Disini", "Testingajakok","akukeren@gmail.com")
//            IntentHome.putExtra(EXTRA_USERDOANK,x)
//            startActivity(IntentHome)
//        }
//        //Intent untuk berpindah ke Activity SignUp dan Menunggu hasil dari activity SignUp
//        btn_signup.setOnClickListener {
//            var IntentSignUp = Intent(this, SignUp::class.java)
//            startActivityForResult(IntentSignUp, EXTRA_REQUEST_CODE)
//        }
//    }
////  Digunakan untuk menangkap hasil(result) dari SignUp melalui pengecekan requestCode, resultCode
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == EXTRA_REQUEST_CODE){
//            if(resultCode == EXTRA_RESULT_CODE){
//                //Men-set Text input_login berdasarkan value yang tersimpan pada Key EXTRA_USERNAME pada SignUp.kt
//                input_login.setText (data?.getStringExtra(EXTRA_USERNAME) ?: "")
//            }
//            else if (resultCode == EXTRA_CANCEL_CODE){
//                input_login.setText("Kosong aja")
//            }
//        }