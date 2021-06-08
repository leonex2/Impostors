package com.example.o_bako.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.o_bako.DatabaseRoom.DBHelperRoom
import com.example.o_bako.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class Login : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        Room Version
        var db = Room.databaseBuilder(
                this,
                DBHelperRoom::class.java,
                "obako.db"
        ).build()

        btn_login.setOnClickListener {
            val intentToMainHome = Intent(this, MainActivity::class.java)
            if (input_login.text.toString().isEmpty() && input_password.text.toString().isEmpty()){
                Toast.makeText(this,"Data tolong diisi",Toast.LENGTH_SHORT).show()
            }
            else{
                var state = false
                var myLogin = input_login.text.toString()
                var myPass = input_password.text.toString()
                doAsync {
                    var userList = db.userDao().getAllData()
                    for(allData in userList){
                        if (myLogin == allData.username){
                            if (myPass == allData.password){
                                startActivity(intentToMainHome)
                                state = true
                                break
                            }
                        }
                    }
                    uiThread {
                        if (state) {
                            Toast.makeText(this@Login, "Login Sukses", Toast.LENGTH_SHORT)
                                    .show()
                        }
                        else
                            Toast.makeText(this@Login,"Username atau Password Salah",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        btn_signup.setOnClickListener{
            val intentSignup = Intent(this, SignUp::class.java)
            startActivity(intentSignup)
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
//
//    private fun writeFileInternal() {
//        var output = openFileOutput("login.txt", Context.MODE_PRIVATE).apply {
//            write(input_login.text.toString().toByteArray())
//            close()
//        }
//        Toast.makeText(this,"File Save",Toast.LENGTH_SHORT).show()
//    }
//
//    private fun readFileInternal() {
//        input_login.text.clear()
//        try{
//            var input = openFileInput("login.txt").apply {
//                bufferedReader().useLines {
//                    for(text in it.toList()){
//                        input_login.setText("${input_login.text}$text")
//                    }
//                }
//            }
//        }
//        catch (e : FileNotFoundException){
//            input_login.setHint("Username")
//        }
//        catch (e : IOException){
//            Toast.makeText(this,"File ERROR !",Toast.LENGTH_SHORT).show()
//        }
//    }
}