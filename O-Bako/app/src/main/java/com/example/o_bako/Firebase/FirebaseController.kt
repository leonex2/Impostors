package com.example.o_bako.Firebase

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseController(context: Context) {
    private var myDatabase = Firebase.database
    private val ref = myDatabase.getReference("USERS")
    private val myContext = context
    private var listUser : MutableList<Users> = mutableListOf()

    fun saveData(users: Users){
        var userID = ref.push().key.toString()
        ref.child(userID).setValue(users).apply {
            addOnCompleteListener{
                Toast.makeText(myContext,"Data Berhasil Tersimpan !", Toast.LENGTH_SHORT).show()
            }
            addOnFailureListener {
                Toast.makeText(myContext,"Data Gagal Tersimpan !", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun readData() : MutableList<Users>{
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    listUser.clear()
                    for(data in snapshot.children){
                        val user = data.getValue(Users::class.java)
                        user.let{
                            listUser.add(Users(it!!.username,it!!.password))
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listUser
    }
    fun myAuth(myLogin : String, myPass : String) : Boolean{
        val getList = readData()
        var state = false
        for (data in getList){
            if(data.username == myLogin && data.password == myPass){
                state = true
                break
            }
        }
        return state
    }
}