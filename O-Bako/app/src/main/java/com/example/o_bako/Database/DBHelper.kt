package com.example.o_bako.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.o_bako.User


@Database(entities = arrayOf(User::class), version = 1)
abstract class DBHelper : RoomDatabase() {
    abstract fun userDao() : UserDao
}