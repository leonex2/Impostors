package com.example.o_bako.Database

import androidx.room.*
import com.example.o_bako.User

@Dao
interface UserDao {
    @Query ("Select * From User")
    fun getAllData () : List<User>

    @Insert
    fun insertUser (vararg user : User)

    @Query ("Delete From User where COLUMN_NAME = :nama_user")
    fun deleteUser (nama_user : String)
}