package com.example.o_bako.DatabaseRoom

import androidx.room.*

@Dao
interface UserDao {
    @Query ("Select * From UserRoom")
    fun getAllData () : List<UserRoom>

    @Insert
    fun insertUser (vararg userRoom : UserRoom)

    @Query("UPDATE UserRoom SET COLUMN_NAME=:value1, COLUMN_ALAMAT=:value2 where COLUMN_ID=:myID")
    fun editData(myID : Int, value1 : String, value2 : String)

    @Query("DELETE FROM UserRoom where COLUMN_ID = :thisID")
    fun deleteAccount(thisID : Int)
}