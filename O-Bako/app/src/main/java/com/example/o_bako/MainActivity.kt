package com.example.o_bako

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.o_bako.fragments.*
import com.example.o_bako.others.MyReceiver
import com.example.o_bako.services.Scheduler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), InterfaceData{

    var userLogin :String? = ""
    var jobID = 14
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
        soonNotified()
        navigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.icon_home -> moveFragment(MainHome())
                R.id.icon_cart -> moveFragment(JenisProduk())
                R.id.icon_history -> moveFragment(History())
                R.id.icon_setting -> moveFragment(Setting())
            }
            true
        }

        var AirPlaneReceiver = MyReceiver()
        var filterku = IntentFilter()
        filterku.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirPlaneReceiver,filterku)

//        melakukan inisialisasi variable userLogin kemudian mengambil data dari EXTRA kemudian
//        mengisi parameter pada fungsi newInstance dengan userLogin
//        kemudian MainActivity diisi dengan fragment MainHome
        var userLogin = intent.getStringExtra(EXTRA_USERNAME)
        val fragment_home = MainHome.newInstance(userLogin.toString())
        supportFragmentManager
            .beginTransaction().replace(R.id.myContainer, fragment_home).commit()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun soonNotified() {
        var serviceComponent = ComponentName(this,Scheduler::class.java)
        var myInfo = JobInfo.Builder(jobID,serviceComponent)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setPeriodic(3*60*1000)
        var jobSchedule = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobSchedule.schedule(myInfo.build())
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