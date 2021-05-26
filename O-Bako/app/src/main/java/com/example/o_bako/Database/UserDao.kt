package com.example.o_bako.Database

import androidx.room.*

@Dao
interface UserDao {
    @Query ("Select * From User")
    fun getAllData () : List<User>

    @Insert
    fun insertUser (vararg user : User)

    @Query("UPDATE User SET COLUMN_NAME=:value1, COLUMN_ALAMAT=:value2 where COLUMN_ID=:myID")
    fun editData(myID : Int, value1 : String, value2 : String)

    @Query("DELETE FROM User where COLUMN_ID = :thisID")
    fun deleteAccount(thisID : Int)
}