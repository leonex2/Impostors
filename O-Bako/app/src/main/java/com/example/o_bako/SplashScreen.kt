package com.example.o_bako

import android.annotation.TargetApi
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
private var splash_soundpool : SoundPool? = null
private var splash_soundID = 0
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            if(splash_soundID!=0) {
                splash_soundpool?.play(splash_soundID, .99f, .99f, 1, 0, .99f)
            }
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, 1800)
    }

    override fun onStart(){
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            createNewSoundPool()
        else
            createOldSoundPool()

        splash_soundpool?.setOnLoadCompleteListener{soundPool, id, status ->
            if(status != 0)
                Toast.makeText(this,"Gagal Load", Toast.LENGTH_SHORT)
                        .show()
            else
                Toast.makeText(this,"Load Sukses", Toast.LENGTH_SHORT)
                        .show()
        }
        splash_soundID = splash_soundpool?.load(this, R.raw.mixkit_arcade_retro,1) ?: 0

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool() {
        splash_soundpool = SoundPool.Builder()
                .setMaxStreams(15)
                .build()
    }
    @Suppress("DEPRECATION")
    private fun createOldSoundPool() {
        splash_soundpool = SoundPool(15, AudioManager.STREAM_MUSIC,0)
    }

    override fun onStop() {
        super.onStop()
        splash_soundpool?.release()
        splash_soundpool = null

    }
}