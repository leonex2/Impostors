package com.example.o_bako

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.o_bako.DatabaseSQLite.DBHelperSQLite
import com.example.o_bako.DatabaseSQLite.Data
import com.example.o_bako.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(){
//    SQLite
    var mySQLite : DBHelperSQLite ?= null
//    Untuk Preload Data
    var myFirstRunPref : PreLoaderShared ?= null
//    Dummy Data
    var dummyDataList = listOf(
            Data(1,"Barang A","Ini Barang A","1","1000"),
            Data(2,"Barang B","Ini Barang B","2","2000"),
            Data(3,"Barang C","Ini Barang C","3","3000"),
            Data(4,"Barang D","Ini Barang D","4","4000"),
            Data(5,"Barang E","Ini Barang E","5","5000"),
            Data(6,"Barang F","Ini Barang F","1","6000"),
            Data(7,"Barang G","Ini Barang G","2","7000"),
            Data(8,"Barang H","Ini Barang H","3","8000"),
            Data(9,"Barang I","Ini Barang I","4","9000"),
            Data(10,"Barang J","Ini Barang J","5","10000"),
            Data(11,"Barang K","Ini Barang K","1","11000"),
            Data(12,"Barang L","Ini Barang L","2","12000"),
            Data(13,"Barang M","Ini Barang M","3","13000"),
            Data(14,"Barang N","Ini Barang N","4","14000"),
            Data(15,"Barang O","Ini Barang O","5","15000"),
            Data(16,"Barang P","Ini Barang P","1","16000"),
            Data(17,"Barang Q","Ini Barang Q","2","17000"),
            Data(18,"Barang R","Ini Barang R","3","18000"),
            Data(19,"Barang S","Ini Barang S","4","19000"),
            Data(20,"Barang T","Ini Barang T","5","20000")
    )

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

        mySQLite = DBHelperSQLite(this)
        myFirstRunPref = PreLoaderShared(this)

        if(myFirstRunPref!!.firstRun){
            addItemTransaction()
        }
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

    private fun addItemTransaction() {
        doAsync {
            mySQLite?.beginDataTransaction()
            for(data in dummyDataList){
                mySQLite?.addDataTranscation(data)
            }
            mySQLite?.successDataTransaction()
            mySQLite?.endDataTransaction()
            uiThread {
                myFirstRunPref?.firstRun = false
                Toast.makeText(this@MainActivity,"Loading Data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}