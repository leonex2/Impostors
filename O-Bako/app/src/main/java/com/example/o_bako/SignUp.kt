package com.example.o_bako

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.File

@RequiresApi(Build.VERSION_CODES.KITKAT)
class SignUp : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        if(isExternalStorageReadable()){
            btn_register.setOnClickListener {
                if(reg_username.text.toString() != "" || reg_passwd.text.toString() != "" || reg_email.text.toString() != "" ){
                    var intentToLoginPage = Intent(this,Login::class.java)
                    writeFileExternal()
                    intentToLoginPage.putExtra(EXTRA_USERSIGNUP,reg_username.text.toString())
                    intentToLoginPage.putExtra(EXTRA_PASSWORDSIGNUP,reg_passwd.text.toString())
                    intentToLoginPage.putExtra(EXTRA_EMAILSIGNUP,reg_email.text.toString())
                    Toast.makeText(this,"Akun Berhasil dibuat!", Toast.LENGTH_SHORT).show()
                    startActivity(intentToLoginPage)
                }
                else{
                    readFileExternal()
                    Toast.makeText(this, "Silahkan isi data dengan benar !", Toast.LENGTH_SHORT).show()
                }
            }
        }
//        btn_register.setOnClickListener {
//            var user = reg_username.text.toString()
//            var user_pw = reg_passwd.text.toString()
//            var user_email = reg_email.text.toString()
//            var mySharedHelper = ShPrefHelper(this,PrefFileName)
//            var intent = Intent()
//            if (user.isEmpty() && user_pw.isEmpty()) {
//                readFileExternal()
//                Toast.makeText(this, "Silahkan isi data dengan benar !", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                writeFileExternal()
//                mySharedHelper.username = user
//                mySharedHelper.password = user_pw
//                mySharedHelper.email = user_email
//                intent.putExtra(EXTRA_USERNAME, user)
//                intent.putExtra(EXTRA_PASSWORD, user_pw)
//                Toast.makeText(this,"Akun Berhasil dibuat!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
//    External File Write&Read
    private fun writeFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        if(!myDir.exists()){
            myDir.mkdir()
        }
        File(myDir,"Sign Up").apply {
            writeText(reg_username.text.toString())
        }
    }
    private fun readFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        var readFile = ""
        File(myDir,"Sign Up").forEachLine(Charsets.UTF_8) {
            readFile += it
        }
        reg_username.setText(readFile)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun isExternalStorageReadable(): Boolean{
        if(ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    123)
        }
        var state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            123 -> {
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Toast.makeText(this, "Allowed !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}