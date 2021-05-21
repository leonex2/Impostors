package com.example.o_bako

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.storage.StorageManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkStorage()
        readFileInternal()
        btn_login.setOnClickListener {
            if(input_login.text.toString() != "" && input_password.text.toString() != ""){
                val intentToMainHome = Intent(this,MainActivity::class.java)
                writeFileInternal()
                var my_Login = input_login.text.toString()
                intentToMainHome.putExtra(EXTRA_USERNAME,my_Login)
                input_login.setText("")
                input_password.setText("")
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                startActivity(intentToMainHome)
            }
            else{
                Toast.makeText(this, "Harap isi Username dan Password!", Toast.LENGTH_SHORT).show()
            }
        }
        btn_signup.setOnClickListener{
            val intentSignup = Intent(this,SignUp::class.java)
            startActivity(intentSignup)
//            startActivityForResult(intentSignup, EXTRA_REQUEST_CODE)
        }
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == EXTRA_REQUEST_CODE){
//            if(resultCode == EXTRA_RESULT_CODE){
//                input_login.setText (data?.getStringExtra(EXTRA_USERNAME) ?: "")
//                input_password.setText(data?.getStringExtra(EXTRA_PASSWORD) ?: "")
//            }
//            else if (resultCode == EXTRA_CANCEL_CODE){
//                input_login.setHint("Username")
//                input_password.setHint("Password")
//            }
//        }
//    }

//Internal File Write&Read

    private fun writeFileInternal() {
        var output = openFileOutput("login.txt", Context.MODE_PRIVATE).apply {
            write(input_login.text.toString().toByteArray())
            close()
        }
        Toast.makeText(this,"File Save",Toast.LENGTH_SHORT).show()
    }

    private fun readFileInternal() {
        input_login.text.clear()
        try{
            var input = openFileInput("login.txt").apply {
                bufferedReader().useLines {
                    for(text in it.toList()){
                        input_login.setText("${input_login.text}\n$text")
                    }
                }
            }
        }
        catch (e : FileNotFoundException){
            input_login.setHint("Username")
        }
        catch (e : IOException){
            Toast.makeText(this,"File ERROR !",Toast.LENGTH_SHORT).show()
        }
    }
    //Check Memory Availability
    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkStorage(){
        val NUM_BYTES_NEEDED_FOR_MY_APP = 1024 * 1024 * 10L
        val storageManager = applicationContext.getSystemService<StorageManager>()!!
        val appSpecificInternalDirUuid: UUID = storageManager.getUuidForPath(filesDir)
        val availableBytes: Long = storageManager.getAllocatableBytes(appSpecificInternalDirUuid)
        if (availableBytes <= NUM_BYTES_NEEDED_FOR_MY_APP) {
            Toast.makeText(this, "Storage < 10MB", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "Storage Tersedia ${availableBytes/1048576} MB", Toast.LENGTH_SHORT).show()
        }
    }
}