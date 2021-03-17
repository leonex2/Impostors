package com.example.o_bako

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.o_bako.fragments.InterfaceData
import com.example.o_bako.fragments.JenisProduk
import com.example.o_bako.fragments.MainHome
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InterfaceData{

    var userLogin :String? = ""
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar.itemIconTintList = null

//        melakukan inisialisasi variable userLogin kemudian mengambil data dari EXTRA kemudian
//        mengisi parameter pada fungsi newInstance dengan userLogin
//        kemudian MainActivity diisi dengan fragment MainHome
        var userLogin = intent.getStringExtra(EXTRA_USERNAME)
        val fragment_home = MainHome.newInstance(userLogin.toString())
        supportFragmentManager
            .beginTransaction().replace(R.id.myContainer, fragment_home).commit()
    }

    override fun Kirim(user: String) {
        val bundle = Bundle()
        bundle.putString("Pesan", user)
        val fragment_jenis = JenisProduk()
        fragment_jenis.arguments = bundle
        val transaksi = this.supportFragmentManager.beginTransaction()
        transaksi.replace(R.id.myContainer, fragment_jenis)
        transaksi.addToBackStack(null)
        transaksi.commit()
    }
}