package com.example.o_bako

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.o_bako.fragments.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

//    var userLogin :String? = ""
    private fun moveFragment(fragment : Fragment){
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.myContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar.itemIconTintList = null

        navigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.icon_home -> moveFragment(MainHome())
                R.id.icon_cart -> moveFragment(JenisProduk())
                R.id.icon_history -> moveFragment(History())
                R.id.icon_setting -> moveFragment(Setting())
            }
            true
        }
        val fragment_home = MainHome()
        supportFragmentManager
                .beginTransaction().replace(R.id.myContainer, fragment_home).commit()
//        melakukan inisialisasi variable userLogin kemudian mengambil data dari EXTRA kemudian
//        mengisi parameter pada fungsi newInstance dengan userLogin
//        kemudian MainActivity diisi dengan fragment MainHome
//        var userLogin = intent.getStringExtra(EXTRA_USERNAME)
//        val fragment_home = MainHome.newInstance(userLogin.toString())
//        supportFragmentManager
//            .beginTransaction().replace(R.id.myContainer, fragment_home).commit()
    }
}