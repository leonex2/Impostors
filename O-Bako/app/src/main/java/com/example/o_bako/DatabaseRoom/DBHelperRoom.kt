package com.example.o_bako.DatabaseRoom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserRoom::class), version = 1)
abstract class DBHelperRoom : RoomDatabase() {
    abstract fun userDao() : UserDao
}